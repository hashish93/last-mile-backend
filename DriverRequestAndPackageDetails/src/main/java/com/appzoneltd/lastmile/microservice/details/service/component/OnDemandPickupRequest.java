package com.appzoneltd.lastmile.microservice.details.service.component;

import com.appzoneltd.lastmile.microservice.details.ObjectMapper;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationModel;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.*;
import com.appzoneltd.lastmile.microservice.details.dao.repos.*;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by alaa.nabil on 3/26/2017.
 */
@Component
public class OnDemandPickupRequest {
    private final PickupRequestJpaRepository pickupRequestRepository;
    private final RequestHistoryJpaRepository requestHistoryRepository;
    private final VerifiedPickupRequestJpaRepository verifiedPickupRequestRepository;
    private final PackageJpaRepository packageRepository;
    private final VerifiedPackageJpaRepository verifiedPackageRepository;
    private final PickupRequestDocumentJpaRepository pickupRequestDocumentRepository;
    private final PackageLabelJpaRepository labelRepository;
    private final PackageTypeJpaRepository packageTypeRepository;
    private final StaticContentJpaRepository staticContentRepository;
    private final RegistrationRepository registrationRepository;
    private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
    private final KafkaTemplate<Long, String> kafkaTemplate;

    @Autowired
    public OnDemandPickupRequest(PickupRequestJpaRepository pickupRequestRepository, RequestHistoryJpaRepository requestHistoryRepository, PackageJpaRepository packageRepository,
                                 VerifiedPickupRequestJpaRepository verifiedPickupRequestRepository, VerifiedPackageJpaRepository verifiedPackageRepository,
                                 PickupRequestDocumentJpaRepository pickupRequestDocumentRepository, PackageLabelJpaRepository labelRepository, PackageTypeJpaRepository packageTypeRepository, StaticContentJpaRepository staticContentRepository,
                                 RegistrationRepository registrationRepository, ActiveVehicleJpaRepository activeVehicleJpaRepository, KafkaTemplate<Long, String> kafkaTemplate) {
        this.pickupRequestRepository = pickupRequestRepository;
        this.requestHistoryRepository = requestHistoryRepository;
        this.packageRepository = packageRepository;
        this.verifiedPickupRequestRepository = verifiedPickupRequestRepository;
        this.verifiedPackageRepository = verifiedPackageRepository;
        this.pickupRequestDocumentRepository = pickupRequestDocumentRepository;
        this.labelRepository = labelRepository;
        this.packageTypeRepository = packageTypeRepository;
        this.staticContentRepository = staticContentRepository;
        this.registrationRepository = registrationRepository;
        this.activeVehicleJpaRepository = activeVehicleJpaRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Object getNotificationModelWithRequestDetailsAndSetHubIdForFreelancerDriver(Long packageId, Long requestId, ActiveVehicleEntity activeVehicleEntity) throws JsonProcessingException {
        PickupRequestEntity pickupRequestEntity = null;
        if (packageId == null || requestId == null || activeVehicleEntity == null)
            return null;
        pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        this.updateFreelancerHubIdWithRequestHubId(activeVehicleEntity.getId(), pickupRequestEntity.getHubId());
        return RequestChain.buildPushNotificationModelWithSerializationToSendToCustomer(packageId, pickupRequestEntity.getRequester().getCustomerId(), requestId, "PICKUP", pickupRequestEntity.getPickuplatitude(), pickupRequestEntity.getPickuplongitude(), activeVehicleEntity);
    }

    private void updateFreelancerHubIdWithRequestHubId(Long activeVehicleEntityId, Long hubId) {
        final RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleEntityId);
        if (registrationModel != null && "FREELANCER_DRIVER".equalsIgnoreCase(registrationModel.getUserType())) {
            registrationModel.setHubId(hubId);
            registrationRepository.save(registrationModel);
        }
    }

    public PackageDetails toPackageDetails(Long requestId, PackageEntity packageEntity) {
        PickupRequestEntity pickupRequestEntity = null;
        if (requestId == null || packageEntity == null)
            return null;
        pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        PackageDetails packageDetails = new PackageDetails();
        packageDetails.setPackageId(packageEntity.getPackageId());
        packageDetails.setRequestId(requestId);
        packageDetails.setRequestType("PICKUP");
        packageDetails.setNickname(packageEntity.getNickname());
        packageDetails.setType(ObjectMapper.toPackageType(packageEntity.getPackageType()));
        packageDetails.setPackageValue(packageEntity.getPackageValue());
        packageDetails.setWeight(packageEntity.getActualweight());
        packageDetails.setDescription(packageEntity.getDescription());
        packageDetails.setWrappingLabel(pickupRequestEntity.getLabelingtext());
        packageDetails.setBoxing(pickupRequestEntity.getAdditionalservices());
        packageDetails.setPackagingLabelsIds(ObjectMapper.toListOfPackageLabelsIds(packageEntity.getListOfPackageLabel()));
        packageDetails.setPaymentType(pickupRequestEntity.getPaymenttype().toUpperCase());
        packageDetails.setPaymentMethod(pickupRequestEntity.getPaymentmethod().toUpperCase());
        packageDetails.setImageIds(ObjectMapper.toListOfPackageImagesIds(packageEntity.getListOfStaticContent()));
        packageDetails.setRequesterId(pickupRequestEntity.getRequester().getCustomerId());
        packageDetails.setRecipientId(pickupRequestEntity.getRecipient() != null ? pickupRequestEntity.getRecipient().getCustomerId() : null);
        return packageDetails;
    }

    public com.appzoneltd.lastmile.microservice.details.dto.JobOrder fillJobOrderDto(JobOrder order) {
        PickupRequestEntity pickupRequestEntity = null;
        if (order == null)
            return null;
        pickupRequestEntity = pickupRequestRepository.findOne(order.getJobOrderId());
        PackageEntity packageEntity = requestHistoryRepository.findByRequestId(order.getJobOrderId()).get(0).getPackageEntity();

        com.appzoneltd.lastmile.microservice.details.dto.JobOrder jobOrder = new com.appzoneltd.lastmile.microservice.details.dto.JobOrder();
        jobOrder.setId(order.getJobOrderId());
        jobOrder.setAddress(pickupRequestEntity.getPickupformatedaddress());
        jobOrder.setLocation(new Location(Double.parseDouble(pickupRequestEntity.getPickuplatitude()), Double.parseDouble(pickupRequestEntity.getPickuplongitude())));
        jobOrder.setPackageId(packageEntity.getPackageId());
        jobOrder.setPackageType(packageEntity.getPackageType().getPackagetype());

        ///////////////////////////////////
        //jobOrder.setPriority(order.getPriority());
        jobOrder.setPriority(0);
        //////////////////////////////////
        jobOrder.setRequestType("PICKUP");
        jobOrder.setWeight(packageEntity.getActualweight());
        jobOrder.setTimeFrom(pickupRequestEntity.getTimeFrom());
        jobOrder.setTimeTo(pickupRequestEntity.getTimeTo());
        jobOrder.setStatus(pickupRequestEntity.getRequestStatus());
        //        jobOrder.setTiming(new JobOrderTiming(order.getDepartureTime(), order.getArrivalTime(),
        //                null, null, order.getEta(), null));
        //        jobOrder.setRejectionReason(order.getRejectionReasonStr());
        //        jobOrder.setResponse(order.getDriverResponse());
        return jobOrder;
    }

    public boolean verifyDetails(PackageDetails packageDetails, Long activeVehicleId)
            throws EntityNotFoundException, JsonProcessingException {
        Boolean result = false;

        PackageEntity packageEntity = packageRepository.findOne(packageDetails.getPackageId());
        PickupRequestEntity pickupRequestEntity = pickupRequestRepository
                .findOne(packageDetails.getRequestId());

        RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleId);

        if (!isVehicleCapacityAcceptable(registrationModel, pickupRequestEntity.getPickupRequestId(),
                packageDetails.getWeight()))
            throw new EntityNotFoundException("no.vehicle.capacity");

        updateCouchbasePackageCapacity(registrationModel, pickupRequestEntity.getPickupRequestId(), packageDetails.getWeight());

        VerifiedPickupRequestEntity verifiedPickupRequestEntity = verifiedPickupRequestRepository
                .save(new VerifiedPickupRequestEntity(pickupRequestEntity.getPickupRequestId(), packageDetails.getBoxing(),
                        packageDetails.getWrappingLabel(), packageDetails.getPaymentType(), packageDetails.getPaymentMethod()));

        if (verifiedPickupRequestEntity == null)
            throw new EntityNotFoundException("Request Not Found");

        Set<PackageLabelEntity> labels = new HashSet<>();
        PackageLabelEntity labelEntity = null;
        for (Long id : packageDetails.getPackagingLabelsIds()) {
            labelEntity = labelRepository.findOne(id);
            labels.add(labelEntity);
        }

        Set<StaticContentEntity> images = new HashSet<>();
        StaticContentEntity contentEntity = null;
        for (Long id : packageDetails.getImageIds()) {
            contentEntity = staticContentRepository.findOne(id);
            images.add(contentEntity);
        }

        packageEntity.setListOfPackageLabel(labels);

        VerifiedPackageEntity verifiedPackageEntity = verifiedPackageRepository.save(
                new VerifiedPackageEntity(packageEntity.getPackageId(), packageDetails.getWeight(), packageDetails.getDescription(),
                        packageTypeRepository.findOne(packageDetails.getType().getPackageTypeId()), packageEntity.getPackageValue(), images));

        if (verifiedPackageEntity == null)
            throw new EntityNotFoundException("Package Not Found");

        if (packageRepository.save(packageEntity) == null)
            throw new EntityNotFoundException("Package Not Found");
        else {
            Invoice invoice = RequestChain.buildInvoiceModel(packageEntity, verifiedPackageEntity, pickupRequestEntity.getPickupRequestType().getType()
                    , pickupRequestEntity.getPickupformatedaddress(), pickupRequestEntity.getRecipientformatedaddress(), verifiedPickupRequestEntity.getPaymentmethod());
            kafkaTemplate.send("push-notification", RequestChain.buildNotificationOfInvoice(packageEntity.getPackageId(),
                    pickupRequestEntity.getRequester().getCustomerId(), invoice));
            result = true;
        }

        return result;
    }

    private boolean isVehicleCapacityAcceptable(RegistrationModel model, Long pickupRequestId, BigDecimal packageWeight) {

        boolean isAcceptable = false;
        BigDecimal vehicleCapacity = new BigDecimal(model.getCapacity());

        BigDecimal totalWeight = BigDecimal.ZERO;

        for (JobOrder order : model.getJobOrders()) {

            if (order.getJobOrderId().equals(pickupRequestId)) {
                continue;
            }
            totalWeight = totalWeight.add(new BigDecimal(order.getActualWeight()));
        }
        vehicleCapacity = vehicleCapacity.subtract(totalWeight);
        if (vehicleCapacity.compareTo(packageWeight) == 1 || vehicleCapacity.compareTo(packageWeight) == 0)
            isAcceptable = true;

        return isAcceptable;
    }

    private void updateCouchbasePackageCapacity(RegistrationModel model, Long pickupRequestId, BigDecimal packageWeight) {
        for (JobOrder order : model.getJobOrders()) {
            if (order.getJobOrderId().equals(pickupRequestId)) {
                order.setActualWeight(packageWeight.intValue());
                break;
            }
        }
        registrationRepository.save(model);
    }

    public Invoice generateInvoice(Long requestId) throws EntityNotFoundException {
        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (pickupRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");

        PackageEntity packageEntity = requestHistoryEntities.get(0).getPackageEntity();

        Invoice invoice = RequestChain.buildInvoiceModel(packageEntity, packageEntity.getVerifiedPackage(), pickupRequestEntity.getPickupRequestType().getType()
                , pickupRequestEntity.getPickupformatedaddress(), pickupRequestEntity.getRecipientformatedaddress(), pickupRequestEntity.getVerifiedPickupRequestEntity().getPaymentmethod());
        return invoice;
    }

    public void confirmInvoice(Long requestId, Long driverId) throws EntityNotFoundException, JsonProcessingException {
        Map<String, Object> data = new HashMap<>();
        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (pickupRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");
        data.put("requestId", requestId);
        data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
        data.put("driverId", driverId);
        kafkaTemplate.send("DRIVER_CONFIRM_INVOICE", RequestChain.MAPPER.writeValueAsString(data));
    }

    public Boolean submitAndAddDocuments(Documents documents, Long driverId) throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError {
        boolean result = false;

        StaticContentEntity customerIdImage = staticContentRepository.findOne(documents.getCustomerIdImageId());
        StaticContentEntity creditCardImage = staticContentRepository.findOne(documents.getCreditCardImageId());
        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(documents.getRequestId());
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository
                .findByRequestId(documents.getRequestId());
        if (pickupRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");

        PickupRequestDocumentEntity pickupRequestDocuments = new PickupRequestDocumentEntity();
        pickupRequestDocuments.setCustomerIdImage(customerIdImage);
        pickupRequestDocuments.setCreditCardImage(creditCardImage);
        pickupRequestDocuments.setBarcode(documents.getBarcode());
        pickupRequestDocuments.setPickupRequest(pickupRequestEntity);
        pickupRequestDocuments.setPickupRequestId(documents.getRequestId());
        pickupRequestDocuments = pickupRequestDocumentRepository.save(pickupRequestDocuments);
        if (pickupRequestDocuments != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("driverId", driverId);
            data.put("requestId", documents.getRequestId());
            data.put("requesterId", pickupRequestEntity.getRequester().getCustomerId());
            data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
            kafkaTemplate.send("DRIVER_SUBMIT_PICKUP_REQUEST", RequestChain.MAPPER.writeValueAsString(data));
            result = true;
        }

        return result;
    }

    public void cancelRequest(CancelRequest cancelRequest) throws JsonProcessingException {
        cancelRequest.setRequesterId(pickupRequestRepository.findOne(cancelRequest.getRequestId()).getRequester().getCustomerId());
        kafkaTemplate.send("CancelPickupRequest", RequestChain.MAPPER.writeValueAsString(cancelRequest));
    }

    public TodaySummary calculateTotalAndCompletedOrders(final RegistrationModel registrationModel) throws EntityNotFoundException {
        return new TodaySummary()
                .addTotalOrders(registrationModel.getJobOrders().size())
                .addPickupOrders(registrationModel.getJobOrders().size())
                .addCompletedOrders(calculateCompletedOrders(registrationModel));
    }

    private int calculateCompletedOrders(RegistrationModel registrationModel) {
        int totalOrders = 0;

        for (JobOrder jobOrder : registrationModel.getJobOrders())
            if ("PICKUP".equalsIgnoreCase(jobOrder.getOrderType()) && "PICKEDUP".equalsIgnoreCase(jobOrder.getOrderStatus()))
                totalOrders++;

        return totalOrders;
    }
}
