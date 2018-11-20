package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.model.ActiveVehicleModel;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.model.JobOrder;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.repository.ActiveVehicleCouchbaseRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.*;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.*;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryStatus;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.DeliveryWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.models.DeliveryPackageStatus;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.DriverNotificationModel;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Random;

@Service
public class DeliveryRequestService {

    @Autowired
    private RequestHistoryJpaRepository requestHistoryRepository;

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    @Autowired
    private PickupRequestJpaRepository pickupRequestJpaRepository;

    @Autowired
    private ActiveVehicleRepository activeVehicleRepository;

    @Autowired
    private ActiveVehicleCouchbaseRepository activeVehicleCouchbaseRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PlanOrdersRepository planOrdersRepository;

    @Autowired
    private DeliveryWorkFlowProducer deliveryWorkFlowProducer;

    @Autowired
    private VerifiedPickupRequestJpaRepository verifiedPickupRequestJpaRepository;


    public void createNewDeliveryRequest(Long packageId) {


        System.out.println(">>> PACKAGE ID " + packageId);
        Long pickupRequestId = requestHistoryRepository.getPackagePickupId(packageId);
        System.out.println(">>> REQUEST ID " + pickupRequestId);
        if (pickupRequestId != null) {
            PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(pickupRequestId);
            VerifiedPickupRequestEntity verifiedPickupRequestEntity = verifiedPickupRequestJpaRepository.findOne(pickupRequestId);
            DeliveryRequestEntity deliveryRequestEntity = buildDeliveryRequest(pickupRequestEntity, verifiedPickupRequestEntity);
            try {
                System.out.println(">>>>>>>> DeliveryRequestEntity " + mapper.writeValueAsString(deliveryRequestEntity));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            deliveryRequestRepository.save(deliveryRequestEntity);

            //// TODO: 5/26/2017 To Be DELETED
            Date date = new Date();
            date.setMinutes(date.getMinutes() + 1);

            RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
            requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
            requestHistoryEntity.setRequestType("DELIVERY");
            requestHistoryEntity.setCreated(date);
            requestHistoryEntity.setRequestStatus(null);
            requestHistoryEntity.setPackageId(packageId);
            requestHistoryEntity.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
            requestHistoryRepository.save(requestHistoryEntity);
        }

    }

    public DeliveryRequestEntity buildDeliveryRequest(PickupRequestEntity pickupRequestEntity, VerifiedPickupRequestEntity verifiedPickupRequestEntity) {
        DeliveryRequestEntity deliveryRequestEntity = new DeliveryRequestEntity();

//		Long deliveryRequestId=Utils.generateUUID();
        Long receipientId = getCustomerIdForMobile(pickupRequestEntity.getRecipientmobile());

        /*getting data from verified pickup request*/
        deliveryRequestEntity.setAdditionalservices(verifiedPickupRequestEntity.getAdditionalservices());
        deliveryRequestEntity.setLabelingtext(verifiedPickupRequestEntity.getLabelingtext());
        deliveryRequestEntity.setPaymentmethod(verifiedPickupRequestEntity.getPaymentmethod());
        deliveryRequestEntity.setPaymenttype(verifiedPickupRequestEntity.getPaymenttype());

        deliveryRequestEntity.setConfirmationCode(null);
        deliveryRequestEntity.setCreated(new Date());
        deliveryRequestEntity.setDeliverydate(null);
        deliveryRequestEntity.setDeliveryRequestId(pickupRequestEntity.getPickupRequestId());
        deliveryRequestEntity.setDescription(pickupRequestEntity.getDescription());
        deliveryRequestEntity.setHubId(pickupRequestEntity.getHubId());


        deliveryRequestEntity.setPickupdate(pickupRequestEntity.getPickupdate());
        deliveryRequestEntity.setPickupformatedaddress(pickupRequestEntity.getPickupformatedaddress());
        deliveryRequestEntity.setPickuplatitude(pickupRequestEntity.getPickuplatitude());
        deliveryRequestEntity.setPickuplongitude(pickupRequestEntity.getPickuplongitude());
        deliveryRequestEntity.setPickupwasellocation(pickupRequestEntity.getPickupwasellocation());
        deliveryRequestEntity.setRecipientadditionalinfo(pickupRequestEntity.getRecipientadditionalinfo());
        deliveryRequestEntity.setRecipientformatedaddress(pickupRequestEntity.getRecipientformatedaddress());
        deliveryRequestEntity.setRecipientId(receipientId);
        deliveryRequestEntity.setRecipientlatitude(pickupRequestEntity.getRecipientlatitude());
        deliveryRequestEntity.setRecipientlongitude(pickupRequestEntity.getRecipientlongitude());
        deliveryRequestEntity.setRecipientmobile(pickupRequestEntity.getRecipientmobile());
        deliveryRequestEntity.setCountryCode(pickupRequestEntity.getCountryCode());
        deliveryRequestEntity.setRecipientname(pickupRequestEntity.getRecipientname());
        deliveryRequestEntity.setRecipientwasellocation(pickupRequestEntity.getRecipientwasellocation());
        deliveryRequestEntity.setRequesterId(pickupRequestEntity.getRequesterId());
        deliveryRequestEntity.setRequestStatus(null);
        deliveryRequestEntity.setRequesttime(pickupRequestEntity.getRequesttime());
        deliveryRequestEntity.setTimeFrom(pickupRequestEntity.getTimeFrom());
        deliveryRequestEntity.setTimeTo(pickupRequestEntity.getTimeTo());
        deliveryRequestEntity.setVersion(0L);

        return deliveryRequestEntity;
    }

    private Long getCustomerIdForMobile(String phoneNumber) {
        Long userId = usersRepository.getCustomerIdForPhoneNumber(phoneNumber);
        System.out.println(">>>>>>>>>>>> user id " + userId);
        return usersRepository.getCustomerIdForPhoneNumber(phoneNumber);
    }

    public DeliveryRequestEntity savingRescheduleDeliveryDate(Long packageId, Date rescheduleDeliveryDate,
                                                              String timeFrom, String timeTo) {

        DeliveryRequestEntity deliveryRequestEntity = getDeliveryRequestForPackage(packageId);

        deliveryRequestEntity.setDeliverydate(rescheduleDeliveryDate);
        deliveryRequestEntity.setTimeFrom(timeFrom);
        deliveryRequestEntity.setTimeTo(timeTo);
        return deliveryRequestRepository.save(deliveryRequestEntity);

    }

    public DeliveryRequestEntity getDeliveryRequestForPackage(Long packageId) {
        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        return deliveryRequestRepository.findOne(deliveryId);
    }

    public Long getCustomerForPackage(Long packageId) {

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        Long requesterId = deliveryRequestEntity.getRequesterId();

        return requesterId;
    }

    public Long getReceipentForPackage(Long packageId) {

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        Long receipentId = deliveryRequestEntity.getRecipientId();

        return receipentId;
    }

    public Long getRequesterForPackage(Long packageId) {

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        Long requesterId = deliveryRequestEntity.getRequesterId();

        return requesterId;
    }

    public String getReceipentMobileNumber(Long packageId) {

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        String mobileNumber = deliveryRequestEntity.getRecipientmobile();

        return mobileNumber;
    }

    public Date getDeliveryDate(Long packageId) {

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        Date deliveryDate = deliveryRequestEntity.getDeliverydate();

        return deliveryDate;
    }

    public String getSenderFullName(Long packageId) {

        String fullName = "Anoymous";

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        Long senderId = deliveryRequestEntity.getRequesterId();

        if (senderId != null) {
            UsersEntity userEntity = usersRepository.findOne(senderId);
            fullName = userEntity.getUsername();
        }

        return fullName;
    }

    public boolean isPaymentAtDelivery(Long packageId) {
        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        String paymentType = deliveryRequestEntity.getPaymenttype();
        boolean atDelivery = false;

        if ("PICKUP".equalsIgnoreCase(paymentType)) {
            atDelivery = false;
        } else if ("DELIVERY".equalsIgnoreCase(paymentType)) {
            atDelivery = true;
        }
        return atDelivery;
    }

    public DriverNotificationModel getDriverDetails(Long packageId, Long driverId, Long requestId, Long activeVehicleId) {

        DriverNotificationModel driverNotificationModel = new DriverNotificationModel();

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        UsersEntity userEntity = usersRepository.findOne(driverId);
        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);

        driverNotificationModel.setRequestId(deliveryId);
        driverNotificationModel.setHubId(deliveryRequestEntity.getHubId());
        driverNotificationModel.setPickupLatitude(deliveryRequestEntity.getRecipientlatitude());
        driverNotificationModel.setPickupLongitude(deliveryRequestEntity.getRecipientlongitude());
        driverNotificationModel.setConfirmationCode(deliveryRequestEntity.getConfirmationCode());
        driverNotificationModel.setRequestId(requestId);

        driverNotificationModel.setDriverId(driverId);
        driverNotificationModel.setDriverName(userEntity.getUsername());
        driverNotificationModel.setDriverImageId(userEntity.getImageId());
        driverNotificationModel.setDriverPhoneNumber(activeVehicleEntity.getDevices().getPhonenumber());
        driverNotificationModel.setDriverRating(activeVehicleEntity.getDriver().getRating());

        driverNotificationModel.setVehicleId(activeVehicleEntity.getVehicle().getVehicleId());
        driverNotificationModel.setVehicleModel(activeVehicleEntity.getVehicle().getModel());
        driverNotificationModel.setVehiclePlateNumber(activeVehicleEntity.getVehicle().getPlate());

        return driverNotificationModel;
    }

    public void changeAllDeliveryRequestsToActionNeeded(Long driverId) {

        ActiveVehicleModel activeVehicleModel = activeVehicleCouchbaseRepository.gettingActiveVehicleModel(driverId);
        // CONVERT NOT DELIVERED TO ACTION NEEDED
        for (JobOrder jobOrder : activeVehicleModel.getJobOrders()) {
            if ((!DeliveryStatus.DELIVERED.name().equals(jobOrder.getOrderStatus()))
                    || (!DeliveryStatus.ACTION_NEEDED.name().equals(jobOrder.getOrderStatus()))) {
                // DELETE FROM COUCHBASE
                activeVehicleCouchbaseRepository.changeRequestStatus(driverId, jobOrder.getJobOrderId(),
                        DeliveryStatus.ACTION_NEEDED.name());
                // CHANGE STATUS
                Long packageId = requestHistoryRepository.getDeliveryPackageId(jobOrder.getJobOrderId());
                if (packageId != null) {
                    DeliveryPackageStatus deliveryPackageStatus = new DeliveryPackageStatus();
                    deliveryPackageStatus.setPackageId(packageId);
                    deliveryPackageStatus.setPackageStatus(DeliveryStatus.ACTION_NEEDED.name());
                    changePackageStatus(deliveryPackageStatus);
                }
            }
        }
    }

    @Transactional
    public boolean changePackageStatus(DeliveryPackageStatus deliveryPackageStatus) {
        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(deliveryPackageStatus.getPackageId());
        Long packageId = deliveryPackageStatus.getPackageId();
        PackageEntity packageEntity = packageRepository.findOne(packageId);
        System.out.println(">>> ATTEMP TO GET PACKAGE ENTITY <<<<");
        if (packageEntity != null) {
            packageEntity.setStatus(deliveryPackageStatus.getPackageStatus());
            packageRepository.save(packageEntity);
            System.out.println(">>> PACKAGE ENTITY  <<<< " + packageEntity.toString());
        }
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        deliveryRequestEntity.setRequestStatus(deliveryPackageStatus.getPackageStatus());
        // Save Delivery
        deliveryRequestRepository.save(deliveryRequestEntity);

        RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
        requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
        requestHistoryEntity.setRequestType("DELIVERY");
        requestHistoryEntity.setCreated(new Date());
        requestHistoryEntity.setRequestStatus(deliveryPackageStatus.getPackageStatus());
        requestHistoryEntity.setPackageId(packageId);
        requestHistoryEntity.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
        requestHistoryRepository.save(requestHistoryEntity);


        PlanOrderEntity planOrderEntity = planOrdersRepository.findByDeliveryId(requestHistoryEntity.getRequestId());
        if (planOrderEntity != null) {
//            WorkflowPackageStatusCouchbase workflowPackageStatusCouchbase = new WorkflowPackageStatusCouchbase();
//            workflowPackageStatusCouchbase.setRequestId(requestHistoryEntity.getRequestId());
//            workflowPackageStatusCouchbase.setStatus(deliveryPackageStatus.getPackageStatus());
//            workflowPackageStatusCouchbase.setDriverId(planOrderEntity.getActiveVehicle().getDriver().getId());

            activeVehicleCouchbaseRepository.changeRequestStatus(planOrderEntity.getActiveVehicle().getDriver().getId(), requestHistoryEntity.getRequestId(), deliveryPackageStatus.getPackageStatus());
        }


        return true;
    }


    public void generateDeliveryConfirmationCodeForPackage(Long packageId) {

        Long deliveryId = requestHistoryRepository.getPackageDeliveryId(packageId);
        String verificationCode = generateVerificationCode(5);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(deliveryId);
        deliveryRequestEntity.setConfirmationCode(verificationCode);
        deliveryRequestRepository.save(deliveryRequestEntity);
    }

    private String generateVerificationCode(int maximumNumberOfDigits) {
        final int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maximumNumberOfDigits; i++) {
            stringBuilder.append(numbers[random.nextInt(10)]);
        }
        return stringBuilder.toString();
    }

}
