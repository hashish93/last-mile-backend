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
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 3/26/2017.
 */
@Component(value = "pickupRequest")
public class PickupRequest implements RequestChain {
    private final PickupRequestJpaRepository pickupRequestRepository;
    private final RequestHistoryJpaRepository requestHistoryRepository;
    private final VerifiedPickupRequestJpaRepository verifiedPickupRequestRepository;
    private final PackageJpaRepository packageRepository;
    private final VerifiedPackageJpaRepository verifiedPackageRepository;
    private final PickupRequestDocumentJpaRepository pickupRequestDocumentRepository;
    private final PackageLabelJpaRepository labelRepository;
    private final PackageTypeJpaRepository packageTypeRepository;
    private final StaticContentJpaRepository staticContentRepository;
    private final UsersJpaRepository usersJpaRepository;
    private final RegistrationRepository registrationRepository;
    private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final RequestChain next;

    @Autowired
    public PickupRequest(PickupRequestJpaRepository pickupRequestRepository, RequestHistoryJpaRepository requestHistoryRepository, PackageJpaRepository packageRepository,
                         VerifiedPickupRequestJpaRepository verifiedPickupRequestRepository, VerifiedPackageJpaRepository verifiedPackageRepository,
                         PickupRequestDocumentJpaRepository pickupRequestDocumentRepository, PackageLabelJpaRepository labelRepository, PackageTypeJpaRepository packageTypeRepository, StaticContentJpaRepository staticContentRepository,
                         UsersJpaRepository usersJpaRepository, RegistrationRepository registrationRepository, ActiveVehicleJpaRepository activeVehicleJpaRepository, KafkaTemplate<Long, String> kafkaTemplate, @Qualifier(value = "deliveryRequest") RequestChain next) {
        this.pickupRequestRepository = pickupRequestRepository;
        this.requestHistoryRepository = requestHistoryRepository;
        this.packageRepository = packageRepository;
        this.verifiedPickupRequestRepository = verifiedPickupRequestRepository;
        this.verifiedPackageRepository = verifiedPackageRepository;
        this.pickupRequestDocumentRepository = pickupRequestDocumentRepository;
        this.labelRepository = labelRepository;
        this.packageTypeRepository = packageTypeRepository;
        this.staticContentRepository = staticContentRepository;
        this.usersJpaRepository = usersJpaRepository;
        this.registrationRepository = registrationRepository;
        this.activeVehicleJpaRepository = activeVehicleJpaRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.next = next;
    }


    @Override
    public LatLng getGoogleLocationLatLng(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        PickupRequestEntity pickupRequestEntity = null;
        if ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            pickupRequestEntity = pickupRequestRepository.findOne(planOrderEntity.getOrderId());
            return new LatLng(Double.parseDouble(pickupRequestEntity.getPickuplatitude()), Double.parseDouble(pickupRequestEntity.getPickuplongitude()));
        }

        return next.getGoogleLocationLatLng(planOrderEntity);
    }

    @Override
    public String getFormattedAddress(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        if ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            return pickupRequestRepository.findOne(planOrderEntity.getOrderId()).getPickupformatedaddress();
        }
        return next.getFormattedAddress(planOrderEntity);
    }

    @Override
    public Location getLocationDto(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        PickupRequestEntity pickupRequestEntity = null;
        if ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            pickupRequestEntity = pickupRequestRepository.findOne(planOrderEntity.getOrderId());
            return new Location(Double.parseDouble(pickupRequestEntity.getPickuplatitude()), Double.parseDouble(pickupRequestEntity.getPickuplongitude()));
        }
        return next.getLocationDto(planOrderEntity);
    }

    @Override
    public BigDecimal getPackageWeight(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        if ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            return planOrderEntity.getPackage().getActualweight();
        }
        return next.getPackageWeight(planOrderEntity);
    }

    @Override
    public String getPackageType(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        if ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            return planOrderEntity.getPackage().getPackageType().getPackagetype();
        }
        return next.getPackageType(planOrderEntity);
    }

    @Override
    public JobOrder fillCouchbaseJobOrderFromPlanOrder(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;


        if ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())) {

            PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(planOrderEntity.getOrderId());
            UsersEntity sender = usersJpaRepository.findOne(pickupRequestEntity.getRequester().getCustomerId());

            return new JobOrder(pickupRequestEntity.getPickupRequestId(), "PICKUP", sender.getUsername(), sender.getPhone()
                    , null
                    , new com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location(pickupRequestEntity.getPickuplatitude(), pickupRequestEntity.getPickuplongitude())
                    , pickupRequestEntity.getRecipientname(), pickupRequestEntity.getRecipientmobile(), pickupRequestEntity.getRequestStatus(), planOrderEntity.getPriority().intValue()
                    , planOrderEntity.getPackage().getActualweight().intValue());
        }
        return next.fillCouchbaseJobOrderFromPlanOrder(planOrderEntity);
    }

    @Override
    public Object getNotificationModelWithRequestDetails(Long packageId, Long requestId, String requestType, ActiveVehicleEntity activeVehicleEntity) throws JsonProcessingException {
        PickupRequestEntity pickupRequestEntity = null;
        if (packageId == null || requestId == null || requestType == null || activeVehicleEntity == null)
            return null;
        if ("PICKUP".equalsIgnoreCase(requestType)) {
            pickupRequestEntity = pickupRequestRepository.findOne(requestId);
            return RequestChain.buildPushNotificationModelWithSerializationToSendToCustomer(packageId, pickupRequestEntity.getRequester().getCustomerId(), requestId, requestType, pickupRequestEntity.getPickuplatitude(), pickupRequestEntity.getPickuplongitude(), activeVehicleEntity);
        }
        return next.getNotificationModelWithRequestDetails(packageId, requestId, requestType, activeVehicleEntity);
    }

    @Override
    public PackageDetails toPackageDetails(Long requestId, String requestType, PackageEntity packageEntity, Long driverId) throws JsonProcessingException {
        PickupRequestEntity pickupRequestEntity = null;
        if (requestId == null || requestType == null || packageEntity == null)
            return null;
        if ("PICKUP".equalsIgnoreCase(requestType)) {
            pickupRequestEntity = pickupRequestRepository.findOne(requestId);
            PackageDetails packageDetails = new PackageDetails();
            packageDetails.setPackageId(packageEntity.getPackageId());
            packageDetails.setRequestId(requestId);
            packageDetails.setRequestType(requestType);
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
        return next.toPackageDetails(requestId, requestType, packageEntity, driverId);
    }

    @Override
    public com.appzoneltd.lastmile.microservice.details.dto.JobOrder fillJobOrderDto(PlanOrderEntity planOrderEntity) {
        PickupRequestEntity pickupRequestEntity = null;
        if (planOrderEntity == null)
            return null;

        if ("PICKUP".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            pickupRequestEntity = pickupRequestRepository.findOne(planOrderEntity.getOrderId());
            com.appzoneltd.lastmile.microservice.details.dto.JobOrder jobOrder = new com.appzoneltd.lastmile.microservice.details.dto.JobOrder();
            jobOrder.setId(planOrderEntity.getOrderId());
            jobOrder.setAddress(pickupRequestEntity.getPickupformatedaddress());
            jobOrder.setLocation(new Location(Double.parseDouble(pickupRequestEntity.getPickuplatitude()), Double.parseDouble(pickupRequestEntity.getPickuplongitude())));
            jobOrder.setPackageId(planOrderEntity.getPackage().getPackageId());
            jobOrder.setPackageType(this.getPackageType(planOrderEntity));
            jobOrder.setPriority(planOrderEntity.getPriority().intValue());
            jobOrder.setRequestType(planOrderEntity.getOrderType());
            jobOrder.setWeight(this.getPackageWeight(planOrderEntity));
            jobOrder.setTimeFrom(pickupRequestEntity.getTimeFrom());
            jobOrder.setTimeTo(pickupRequestEntity.getTimeTo());
            jobOrder.setTiming(new JobOrderTiming(planOrderEntity.getDepartureTime(), planOrderEntity.getArrivalTime(),
                    null, null, planOrderEntity.getEta(), null));
            jobOrder.setRejectionReason(planOrderEntity.getRejectionReasonStr());
            jobOrder.setResponse(DriverResponse.ACCEPT.name());
            jobOrder.setStatus(pickupRequestEntity.getRequestStatus());
            jobOrder.setExcluded(planOrderEntity.getExcluded());
            return jobOrder;
        }

        return next.fillJobOrderDto(planOrderEntity);
    }

    @Override
    public boolean verifyDetails(PackageDetails packageDetails, Long activeVehicleId) throws EntityNotFoundException, JsonProcessingException {
        if (!"PICKUP".equalsIgnoreCase(packageDetails.getRequestType()))
            return next.verifyDetails(packageDetails, activeVehicleId);

        Boolean result = false;

        PackageEntity packageEntity = packageRepository.findOne(packageDetails.getPackageId());
        PickupRequestEntity pickupRequestEntity = pickupRequestRepository
                .findOne(packageDetails.getRequestId());

        if (!isVehicleCapacityAcceptable(activeVehicleId, pickupRequestEntity.getPickupRequestId(),
                packageDetails.getWeight()))
            throw new EntityNotFoundException("no.vehicle.capacity");

        updateCouchbasePackageCapacity(activeVehicleId, pickupRequestEntity.getPickupRequestId(), packageDetails.getWeight());

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
                        packageTypeRepository.findOne(packageDetails.getType().getPackageTypeId()), packageDetails.getPackageValue(), images));

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

    @Override
    public Invoice generateInvoice(Long requestId, String requestType) throws EntityNotFoundException {
        if (!"PICKUP".equalsIgnoreCase(requestType))
            return next.generateInvoice(requestId, requestType);

        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (pickupRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");

        PackageEntity packageEntity = requestHistoryEntities.get(0).getPackageEntity();

        Invoice invoice = RequestChain.buildInvoiceModel(packageEntity, packageEntity.getVerifiedPackage(), pickupRequestEntity.getPickupRequestType().getType()
                , pickupRequestEntity.getPickupformatedaddress(), pickupRequestEntity.getRecipientformatedaddress(), pickupRequestEntity.getVerifiedPickupRequestEntity().getPaymentmethod());
        return invoice;
    }

    @Override
    public void confirmInvoice(Long requestId, String requestType, Long driverId) throws EntityNotFoundException, JsonProcessingException {
        if (!"PICKUP".equalsIgnoreCase(requestType))
            next.confirmInvoice(requestId, requestType, driverId);

        Map<String, Object> data = new HashMap<>();
        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (pickupRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");
        data.put("requestId", requestId);
        data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
        data.put("driverId", driverId);
        kafkaTemplate.send("DRIVER_CONFIRM_INVOICE", MAPPER.writeValueAsString(data));
    }

    @Override
    public Boolean submitAndAddDocuments(Documents documents, Long activeVehicleId) throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError {

        if (!"PICKUP".equalsIgnoreCase(documents.getRequestType()))
            return next.submitAndAddDocuments(documents, activeVehicleId);

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
            data.put("driverId", activeVehicleJpaRepository.findOne(activeVehicleId).getDriver().getId());
            data.put("requestId", documents.getRequestId());
            data.put("requesterId", pickupRequestEntity.getRequester().getCustomerId());
            data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
            kafkaTemplate.send("DRIVER_SUBMIT_PICKUP_REQUEST", RequestChain.MAPPER.writeValueAsString(data));
            result = true;
        }

        return result;
    }

    @Override
    public RequestDetails fillOrderDetails(Long requestId, String requestType) throws EntityNotFoundException {
        if (!"PICKUP".equalsIgnoreCase(requestType))
            return next.fillOrderDetails(requestId, requestType);

        PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
        PackageEntity packageEntity = null;
        UsersEntity usersEntity = null;
        if (pickupRequestEntity == null)
            throw new EntityNotFoundException("package.notfound");

        packageEntity = requestHistoryRepository.findByRequestId(pickupRequestEntity.getPickupRequestId()).get(0).getPackageEntity();

        if (pickupRequestEntity.getRecipient() != null)
            usersEntity = usersJpaRepository.findOne(pickupRequestEntity.getRecipient().getCustomerId());

        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setId(pickupRequestEntity.getPickupRequestId());
        requestDetails.setPaymentAt(pickupRequestEntity.getPaymenttype());
        requestDetails.setPaymentMethod(pickupRequestEntity.getPaymentmethod());
        requestDetails.setShipmentService(packageEntity.getShipmentServiceType().getShipmentService().getService());
        requestDetails.setShipmentServiceType(packageEntity.getShipmentServiceType().getType());
        requestDetails.setPickupTime(pickupRequestEntity.getTimeFrom() == null ? "Now" : pickupRequestEntity.getTimeFrom() + " - " + pickupRequestEntity.getTimeTo());
        requestDetails.setPickupAddress(pickupRequestEntity.getPickupformatedaddress());
        requestDetails.setNickName(packageEntity.getNickname());
        requestDetails.setPackageType(packageEntity.getPackageType().getPackagetype());
        requestDetails.setPackageWeight(packageEntity.getActualweight().toEngineeringString());
        requestDetails.setWhatInside(packageEntity.getDescription());
        requestDetails.setAdditionalServices(pickupRequestEntity.getAdditionalservices());
        requestDetails.setRecipientName(pickupRequestEntity.getRecipientname());
        requestDetails.setRecipientMobileNumber(usersEntity != null ? usersEntity.getPhone() : pickupRequestEntity.getRecipientmobile());
        requestDetails.setRecipientLocation(pickupRequestEntity.getRecipientformatedaddress());
        requestDetails.setAdditionalNotes(pickupRequestEntity.getDescription());
        requestDetails.setImageIds(packageEntity.getListOfStaticContent().stream().map(image -> image.getContentId()).collect(Collectors.toList()));
        requestDetails.setPackageValue(packageEntity.getPackageValue());
        return requestDetails;
    }

    @Override
    public void cancelRequest(CancelRequest cancelRequest) throws JsonProcessingException {
        if (!"PICKUP".equalsIgnoreCase(cancelRequest.getRequestType())) {
            System.out.println("NOT PICKUP");
            next.cancelRequest(cancelRequest);
        } else {
            cancelRequest.setRequesterId(pickupRequestRepository.findOne(cancelRequest.getRequestId()).getRequester().getCustomerId());
            kafkaTemplate.send("CancelPickupRequest", MAPPER.writeValueAsString(cancelRequest));
        }
    }

    @Override
    public boolean isCompletedOrder(Long orderId, String orderType, TodaySummary todaySummary) {
        if (!"PICKUP".equalsIgnoreCase(orderType)) {
            return next.isCompletedOrder(orderId, orderType, todaySummary);
        }
        todaySummary.addPickupOrders(1);
        final PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(orderId);
        if ("PICKEDUP".equalsIgnoreCase(pickupRequestEntity.getRequestStatus())) {
            return true;
        }
        return false;
    }


    private boolean isVehicleCapacityAcceptable(Long activeVehicleId, Long pickupRequestId, BigDecimal packageWeight) {
        boolean isAcceptable = false;
        RegistrationModel model = registrationRepository.findOne(activeVehicleId);
        BigDecimal vehicleCapacity = activeVehicleJpaRepository.findOne(activeVehicleId).getVehicle().getWeight();
        BigDecimal totalWeight = BigDecimal.ZERO;
        for (JobOrder order : model.getJobOrders()) {
            if (order.getJobOrderId().equals(pickupRequestId))
                continue;
            totalWeight = totalWeight.add(new BigDecimal(order.getActualWeight()));
        }
        vehicleCapacity = vehicleCapacity.subtract(totalWeight);
        if (vehicleCapacity.compareTo(packageWeight) == 1 || vehicleCapacity.compareTo(packageWeight) == 0)
            isAcceptable = true;

        return isAcceptable;
    }

    private void updateCouchbasePackageCapacity(Long activeVehicleId, Long pickupRequestId, BigDecimal packageWeight) {
        RegistrationModel model = registrationRepository.findOne(activeVehicleId);
        for (JobOrder order : model.getJobOrders()) {
            if (order.getJobOrderId().equals(pickupRequestId)) {
                order.setActualWeight(packageWeight.intValue());
                break;
            }
        }
        registrationRepository.save(model);
    }
}
