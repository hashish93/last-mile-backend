package com.appzoneltd.lastmile.microservice.details.service.component;

import com.appzoneltd.lastmile.microservice.details.ObjectMapper;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder;
import com.appzoneltd.lastmile.microservice.details.dao.entity.*;
import com.appzoneltd.lastmile.microservice.details.dao.repos.*;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 3/26/2017.
 */
@Component(value = "deliveryRequest")
public class DeliveryRequest implements RequestChain {

    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DeliveryRequestDocumentRepository deliveryRequestDocumentRepository;
    private final PickupRequestJpaRepository pickupRequestRepository;
    private final RequestHistoryJpaRepository requestHistoryRepository;
    private final StaticContentJpaRepository staticContentRepository;
    private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
    private final UsersJpaRepository usersJpaRepository;
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final MessageSource MessageSource;
    private final RequestChain next;

    @Autowired
    public DeliveryRequest(DeliveryRequestRepository deliveryRequestRepository, DeliveryRequestDocumentRepository deliveryRequestDocumentRepository, PickupRequestJpaRepository pickupRequestRepository, RequestHistoryJpaRepository requestHistoryRepository, StaticContentJpaRepository staticContentRepository, ActiveVehicleJpaRepository activeVehicleJpaRepository, UsersJpaRepository usersJpaRepository, KafkaTemplate<Long, String> kafkaTemplate, org.springframework.context.MessageSource messageSource, @Qualifier(value = "returnRequest") RequestChain next) {
        this.deliveryRequestRepository = deliveryRequestRepository;
        this.deliveryRequestDocumentRepository = deliveryRequestDocumentRepository;
        this.pickupRequestRepository = pickupRequestRepository;
        this.requestHistoryRepository = requestHistoryRepository;
        this.staticContentRepository = staticContentRepository;
        this.activeVehicleJpaRepository = activeVehicleJpaRepository;
        this.usersJpaRepository = usersJpaRepository;
        this.kafkaTemplate = kafkaTemplate;
        MessageSource = messageSource;
        this.next = next;
    }

    @Override
    public LatLng getGoogleLocationLatLng(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        DeliveryRequestEntity deliveryRequestEntity;
        if ("DELIVERY".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            deliveryRequestEntity = deliveryRequestRepository.findOne(planOrderEntity.getOrderId());
            return new LatLng(Double.parseDouble(deliveryRequestEntity.getPickuplatitude()), Double.parseDouble(deliveryRequestEntity.getPickuplongitude()));
        }

        return next.getGoogleLocationLatLng(planOrderEntity);
    }

    @Override
    public String getFormattedAddress(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        if ("DELIVERY".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            return deliveryRequestRepository.findOne(planOrderEntity.getOrderId()).getRecipientformatedaddress();
        }

        return next.getFormattedAddress(planOrderEntity);
    }

    @Override
    public Location getLocationDto(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        DeliveryRequestEntity deliveryRequestEntity;
        if ("DELIVERY".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            deliveryRequestEntity = deliveryRequestRepository.findOne(planOrderEntity.getOrderId());

            return new Location(Double.parseDouble(deliveryRequestEntity.getRecipientlatitude()), Double.parseDouble(deliveryRequestEntity.getRecipientlongitude()));
        }
        return next.getLocationDto(planOrderEntity);
    }

    @Override
    public BigDecimal getPackageWeight(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        if ("DELIVERY".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            return planOrderEntity.getPackage().getVerifiedPackage().getActualweight();
        }
        return next.getPackageWeight(planOrderEntity);
    }

    @Override
    public String getPackageType(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;

        if ("DELIVERY".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            return planOrderEntity.getPackage().getVerifiedPackage().getPackageType().getPackagetype();
        }
        return next.getPackageType(planOrderEntity);
    }

    @Override
    public JobOrder fillCouchbaseJobOrderFromPlanOrder(PlanOrderEntity planOrderEntity) {
        if (planOrderEntity == null)
            return null;


        if ("DELIVERY".equalsIgnoreCase(planOrderEntity.getOrderType())) {

            DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(planOrderEntity.getOrderId());
            UsersEntity sender = usersJpaRepository.findOne(deliveryRequestEntity.getRequester().getCustomerId());

            return new JobOrder(deliveryRequestEntity.getDeliveryRequestId(), "DELIVERY", sender.getUsername(), sender.getPhone()
                    , null
                    , new com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location(deliveryRequestEntity.getRecipientlatitude(), deliveryRequestEntity.getRecipientlongitude())
                    , deliveryRequestEntity.getRecipientname(), deliveryRequestEntity.getRecipientmobile(), deliveryRequestEntity.getRequestStatus(), planOrderEntity.getPriority().intValue()
                    , planOrderEntity.getPackage().getVerifiedPackage().getActualweight().intValue());
        }
        return next.fillCouchbaseJobOrderFromPlanOrder(planOrderEntity);
    }

    @Override
    public Object getNotificationModelWithRequestDetails(Long packageId, Long requestId, String requestType, ActiveVehicleEntity activeVehicleEntity) throws JsonProcessingException {
        DeliveryRequestEntity deliveryRequestEntity = null;
        if (packageId == null || requestId == null || requestType == null || activeVehicleEntity == null)
            return null;
        if ("DELIVERY".equalsIgnoreCase(requestType)) {
            deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
            //            if (deliveryRequestEntity.getRecipient() != null)
            //                return RequestChain.buildPushNotificationModelWithSerializationToSendToCustomer(packageId, deliveryRequestEntity.getRecipient().getCustomerId(), requestId, requestType, deliveryRequestEntity.getRecipientlatitude(), deliveryRequestEntity.getRecipientlongitude(), activeVehicleEntity);
            UnRegisteredSmsModel unRegisteredSmsModel = new UnRegisteredSmsModel(deliveryRequestEntity.getCountrycode() != null ?
                    deliveryRequestEntity.getCountrycode() : "" + deliveryRequestEntity.getRecipientmobile(), null);
            //            kafkaTemplate.send("SMS-NOTIFICATION-UNREGISTERED", MAPPER.writeValueAsString(unRegisteredSmsModel));
            Map<String, Object> map = new HashMap<>();
            map.put("packageId", packageId);
            map.put("driverId", activeVehicleEntity.getDriver().getId());
            map.put("activeVehicleId", activeVehicleEntity.getId());
            kafkaTemplate.send("DELIVERY_DRIVER_START_NAVIGATION", MAPPER.writeValueAsString(map));
            return unRegisteredSmsModel;
        }
        return next.getNotificationModelWithRequestDetails(packageId, requestId, requestType, activeVehicleEntity);
    }

    @Override
    public PackageDetails toPackageDetails(Long requestId, String requestType, PackageEntity packageEntity, Long driverId) throws JsonProcessingException {
        DeliveryRequestEntity deliveryRequestEntity = null;
        if (requestId == null || requestType == null || packageEntity == null)
            return null;
        if ("DELIVERY".equalsIgnoreCase(requestType)) {
            deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
            PackageDetails packageDetails = new PackageDetails();
            packageDetails.setPackageId(packageEntity.getPackageId());
            packageDetails.setRequestId(requestId);
            packageDetails.setRequestType(requestType);
            packageDetails.setNickname(packageEntity.getNickname());
            packageDetails.setType(ObjectMapper.toPackageType(packageEntity.getVerifiedPackage().getPackageType()));
            packageDetails.setPackageValue(packageEntity.getVerifiedPackage().getPackageValue());
            packageDetails.setWeight(packageEntity.getVerifiedPackage().getActualweight());
            packageDetails.setDescription(packageEntity.getDescription());
            packageDetails.setWrappingLabel(deliveryRequestEntity.getLabelingtext());
            packageDetails.setBoxing(deliveryRequestEntity.getAdditionalservices());
            packageDetails.setPackagingLabelsIds(ObjectMapper.toListOfPackageLabelsIds(packageEntity.getListOfPackageLabel()));
            packageDetails.setPaymentType(deliveryRequestEntity.getPaymenttype().toUpperCase());
            packageDetails.setPaymentMethod(deliveryRequestEntity.getPaymentmethod().toUpperCase());
            packageDetails.setImageIds(ObjectMapper.toListOfPackageImagesIds(packageEntity.getListOfStaticContent()));
            packageDetails.setRequesterId(deliveryRequestEntity.getRequester().getCustomerId());
            packageDetails.setRecipientId(deliveryRequestEntity.getRecipient() != null ? deliveryRequestEntity.getRecipient().getCustomerId() : null);
            //            packageDetails.setConfirmationCode(deliveryRequestEntity.getConfirmationCode());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("packageId", packageEntity.getPackageId());
            kafkaTemplate.send("DELIVERY_DRIVER_ARRIVED", MAPPER.writeValueAsString(map));
            return packageDetails;
        }
        return next.toPackageDetails(requestId, requestType, packageEntity, driverId);
    }

    @Override
    public com.appzoneltd.lastmile.microservice.details.dto.JobOrder fillJobOrderDto(PlanOrderEntity planOrderEntity) {
        DeliveryRequestEntity deliveryRequestEntity = null;
        if (planOrderEntity == null)
            return null;

        if ("DELIVERY".equalsIgnoreCase(planOrderEntity.getOrderType())) {
            deliveryRequestEntity = deliveryRequestRepository.findOne(planOrderEntity.getOrderId());
            com.appzoneltd.lastmile.microservice.details.dto.JobOrder jobOrder = new com.appzoneltd.lastmile.microservice.details.dto.JobOrder();
            jobOrder.setId(planOrderEntity.getOrderId());
            jobOrder.setAddress(deliveryRequestEntity.getRecipientformatedaddress());
            jobOrder.setLocation(new Location(Double.parseDouble(deliveryRequestEntity.getRecipientlatitude()), Double.parseDouble(deliveryRequestEntity.getRecipientlongitude())));
            jobOrder.setPackageId(planOrderEntity.getPackage().getPackageId());
            jobOrder.setPackageType(this.getPackageType(planOrderEntity));
            jobOrder.setPriority(planOrderEntity.getPriority().intValue());
            jobOrder.setRequestType(planOrderEntity.getOrderType());
            jobOrder.setWeight(this.getPackageWeight(planOrderEntity));
            jobOrder.setTimeFrom(deliveryRequestEntity.getTimeFrom());
            jobOrder.setTimeTo(deliveryRequestEntity.getTimeTo());
            jobOrder.setTiming(new JobOrderTiming(planOrderEntity.getDepartureTime(), planOrderEntity.getArrivalTime(),
                    null, null, planOrderEntity.getEta(), null));
            jobOrder.setRejectionReason(planOrderEntity.getRejectionReasonStr());
            jobOrder.setResponse(planOrderEntity.getDriverResponse());
            jobOrder.setStatus(deliveryRequestEntity.getRequestStatus());
            jobOrder.setExcluded(planOrderEntity.getExcluded());
            return jobOrder;
        }

        return next.fillJobOrderDto(planOrderEntity);
    }

    @Override
    public boolean verifyDetails(PackageDetails packageDetails, Long activeVehicleId) throws EntityNotFoundException, JsonProcessingException {
        if (!"DELIVERY".equalsIgnoreCase(packageDetails.getRequestType()))
            return next.verifyDetails(packageDetails, activeVehicleId);
        return false;
    }

    @Override
    public Invoice generateInvoice(Long requestId, String requestType) throws EntityNotFoundException {
        if (!"DELIVERY".equalsIgnoreCase(requestType))
            return next.generateInvoice(requestId, requestType);

        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (deliveryRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");
        PackageEntity packageEntity = requestHistoryEntities.get(0).getPackageEntity();
        Invoice invoice = RequestChain.buildInvoiceModel(packageEntity, packageEntity.getVerifiedPackage(), pickupRequestRepository.findOne(requestId).getPickupRequestType().getType()
                , deliveryRequestEntity.getPickupformatedaddress(), deliveryRequestEntity.getRecipientformatedaddress(), deliveryRequestEntity.getPaymentmethod());
        return invoice;
    }

    @Override
    public void confirmInvoice(Long requestId, String requestType, Long driverId) throws EntityNotFoundException, JsonProcessingException {
        if (!"DELIVERY".equalsIgnoreCase(requestType))
            next.confirmInvoice(requestId, requestType, driverId);

        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (deliveryRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");
    }

    @Override
    public Boolean submitAndAddDocuments(Documents documents, Long activeVehicleId) throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError {
        if (!"DELIVERY".equalsIgnoreCase(documents.getRequestType()))
            return next.submitAndAddDocuments(documents, activeVehicleId);

        boolean result = false;

        StaticContentEntity customerIdImage = staticContentRepository.findOne(documents.getCustomerIdImageId());
        StaticContentEntity creditCardImage = staticContentRepository.findOne(documents.getCreditCardImageId());
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(documents.getRequestId());
        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository
                .findByRequestId(documents.getRequestId());
        if (deliveryRequestEntity == null || requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");

        if (!deliveryRequestEntity.getConfirmationCode().equalsIgnoreCase(documents.getConfirmationCode()))
            throw new ConfirmationCodeError(MessageSource.getMessage("invalid.confirmation.code", null, LocaleContextHolder.getLocale()));

        DeliveryRequestDocumentEntity deliveryRequestDocumentEntity = new DeliveryRequestDocumentEntity();
        deliveryRequestDocumentEntity.setCustomerIdImage(customerIdImage);
        deliveryRequestDocumentEntity.setCreditCardImage(creditCardImage);
        deliveryRequestDocumentEntity.setBarcode(documents.getBarcode());
        deliveryRequestDocumentEntity.setDeliveryRequest(deliveryRequestEntity);
        deliveryRequestDocumentEntity.setDeliveryRequestId(documents.getRequestId());
        deliveryRequestDocumentEntity = deliveryRequestDocumentRepository.save(deliveryRequestDocumentEntity);

        if (deliveryRequestDocumentEntity != null) {
            //            Map<String, Object> data = new HashMap<>();
            //            data.put("driverId", activeVehicleJpaRepository.findOne(activeVehicleId).getDriver().getId());
            //            data.put("requestId", documents.getRequestId());
            //            data.put("recipientId", deliveryRequestEntity.getRecipient() != null ? deliveryRequestEntity.getRecipient().getCustomerId() : null);
            //
            //            data.put("recipientNumber", deliveryRequestEntity.getRecipientmobile());
            //            data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
            //            kafkaTemplate.send("DRIVER_SUBMIT_DELIVERY_REQUEST", MAPPER.writeValueAsString(data));
            Map<String, Object> data = new HashMap<>();
            data.put("requestId", deliveryRequestEntity.getDeliveryRequestId());
            data.put("driverId", activeVehicleJpaRepository.findOne(activeVehicleId).getDriver().getId());
            data.put("packageId", requestHistoryEntities.get(0).getPackageEntity().getPackageId());
            data.put("customerApproveInvoice", true);
            kafkaTemplate.send("DELIVERY_APPROVE_INVOICE", MAPPER.writeValueAsString(data));
            result = true;
        }

        return result;
    }

    @Override
    public RequestDetails fillOrderDetails(Long requestId, String requestType) throws EntityNotFoundException {
        if (!"DELIVERY".equalsIgnoreCase(requestType))
            return next.fillOrderDetails(requestId, requestType);

        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(requestId);
        PackageEntity packageEntity = null;
        UsersEntity usersEntity = null;
        if (deliveryRequestEntity == null)
            throw new EntityNotFoundException("package.notfound");

        packageEntity = requestHistoryRepository.findByRequestId(deliveryRequestEntity.getDeliveryRequestId()).get(0).getPackageEntity();

        if (deliveryRequestEntity.getRecipient() != null)
            usersEntity = usersJpaRepository.findOne(deliveryRequestEntity.getRecipient().getCustomerId());

        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setId(deliveryRequestEntity.getDeliveryRequestId());
        requestDetails.setPaymentAt(deliveryRequestEntity.getPaymenttype());
        requestDetails.setPaymentMethod(deliveryRequestEntity.getPaymentmethod());
        requestDetails.setShipmentService(packageEntity.getShipmentServiceType().getShipmentService().getService());
        requestDetails.setShipmentServiceType(packageEntity.getShipmentServiceType().getType());
        requestDetails.setPickupTime(deliveryRequestEntity.getTimeFrom() == null ? "Now" : deliveryRequestEntity.getTimeFrom() + " - " + deliveryRequestEntity.getTimeTo());
        requestDetails.setPickupAddress(deliveryRequestEntity.getPickupformatedaddress());
        requestDetails.setNickName(packageEntity.getNickname());
        requestDetails.setPackageType(packageEntity.getVerifiedPackage().getPackageType().getPackagetype());
        requestDetails.setPackageWeight(packageEntity.getVerifiedPackage().getActualweight().toEngineeringString());
        requestDetails.setWhatInside(packageEntity.getVerifiedPackage().getDescription());
        requestDetails.setAdditionalServices(deliveryRequestEntity.getAdditionalservices());
        requestDetails.setRecipientName(deliveryRequestEntity.getRecipientname());
        requestDetails.setRecipientMobileNumber(usersEntity != null ? usersEntity.getPhone() : deliveryRequestEntity.getRecipientmobile());
        requestDetails.setRecipientLocation(deliveryRequestEntity.getRecipientformatedaddress());
        requestDetails.setAdditionalNotes(deliveryRequestEntity.getDescription());
        requestDetails.setImageIds(packageEntity.getVerifiedPackage().getListOfStaticContent().stream().map(image -> image.getContentId()).collect(Collectors.toList()));
        requestDetails.setPackageValue(packageEntity.getPackageValue());

        return requestDetails;
    }

    @Override
    public void cancelRequest(CancelRequest cancelRequest) throws JsonProcessingException {

        if (!"DELIVERY".equalsIgnoreCase(cancelRequest.getRequestType())) {
            next.cancelRequest(cancelRequest);
        } else {
            switch (cancelRequest.getRejectionPhase()) {
                //in these cases: we will trigger the AcTION_NEEDED of the delivery
                case PACKAGE_VERIFICATION_REJECTED:
                case CUSTOMER_NOT_FOUND:
                    cancelRequest.setRequesterId(deliveryRequestRepository.findOne(cancelRequest.getRequestId()).getRequester().getCustomerId());
                    Map<String, Object> data1 = new HashMap<>();
                    data1.put("packageId", requestHistoryRepository.findByRequestId(cancelRequest.getRequestId()).get(0).getPackageEntity().getPackageId());
                    data1.put("customerFound", false);
                    System.out.println(">>>>>>>>>>>>>>>> sending to topic:: DELIVERY_CUSTOMER_FOUND - due to rejection phase:: " + cancelRequest.getRejectionPhase());
                    kafkaTemplate.send("DELIVERY_CUSTOMER_FOUND", MAPPER.writeValueAsString(data1));
                    break;

                //in this case we will trigger the return flow
                case INVOICE_REJECTED:
                case SUBMIT_DOCUMENT_REJECTED:
                    cancelRequest.setRequesterId(deliveryRequestRepository.findOne(cancelRequest.getRequestId()).getRequester().getCustomerId());
                    Map<String, Object> data = new HashMap<>();
                    data.put("packageId", requestHistoryRepository.findByRequestId(cancelRequest.getRequestId()).get(0).getPackageEntity().getPackageId());
                    data.put("customerApproveInvoice", false);
                    System.out.println("&&&&&&&&&>>>>>>>>>>>>>>>> sending to topic:: DELIVERY_APPROVE_INVOICE - due to rejection phase:: " + cancelRequest.getRejectionPhase());
                    kafkaTemplate.send("DELIVERY_APPROVE_INVOICE", MAPPER.writeValueAsString(data));
                    break;

                default:
                    break;
            }
        }

        //        kafkaTemplate.send("CancelDeliveryRequest", MAPPER.writeValueAsString(cancelRequest));
    }

    @Override
    public boolean isCompletedOrder(Long orderId, String orderType, TodaySummary todaySummary) {
        if (!"DELIVERY".equalsIgnoreCase(orderType)) {
            return next.isCompletedOrder(orderId, orderType, todaySummary);
        }
        todaySummary.addDeliveryOrders(1);
        final DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(orderId);
        if ("DELIVERED".equalsIgnoreCase(deliveryRequestEntity.getRequestStatus())) {
            return true;
        }
        return false;
    }
}
