package com.appzoneltd.lastmile.microservice.returnworkflow.business.service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.couchbase.model.ActiveVehicleModel;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.couchbase.model.JobOrder;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.couchbase.repository.ActiveVehicleCouchbaseRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.*;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.*;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnStatus;
import com.appzoneltd.lastmile.microservice.returnworkflow.kafka.ReturnWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.returnworkflow.kafka.models.ReturnPackageStatus;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.DriverNotificationModel;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReturnRequestService {

    @Autowired
    private RequestHistoryJpaRepository requestHistoryRepository;

    @Autowired
    private PickupRequestJpaRepository pickupRequestJpaRepository;

    @Autowired
    private ReturnRequestRepository returnRequestRepository;

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ActiveVehicleRepository activeVehicleRepository;

    @Autowired
    private ActiveVehicleCouchbaseRepository activeVehicleCouchbaseRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private PlanOrdersRepository planOrdersRepository;

    @Autowired
    private ReturnWorkFlowProducer returnWorkFlowProducer;

    public void createNewReturnRequest(Long packageId) {

        Long pickupRequestId = requestHistoryRepository.getPackagePickupId(packageId);
        if (pickupRequestId != null) {

            ReturnRequestEntity returnRequestEntity = buildReturnRequest(pickupRequestId);
            try {
                System.out.println(">>>>>>>> DeliveryRequestEntity " + mapper.writeValueAsString(returnRequestEntity));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            returnRequestRepository.save(returnRequestEntity);

            RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
            requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
            requestHistoryEntity.setRequestType("RETURN");
            requestHistoryEntity.setCreated(new Date());
            requestHistoryEntity.setRequestStatus(null);
            requestHistoryEntity.setPackageId(packageId);
            requestHistoryEntity.setRequestId(returnRequestEntity.getReturnRequestId());
            requestHistoryRepository.save(requestHistoryEntity);
        }

    }

    public ReturnRequestEntity buildReturnRequest(Long pickupRequestId) {
        PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(pickupRequestId);
        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(pickupRequestId);
        ReturnRequestEntity returnRequestEntity = new ReturnRequestEntity();

        returnRequestEntity.setAdditionalservices(pickupRequestEntity.getAdditionalservices());
        returnRequestEntity.setCreated(new Date());
        returnRequestEntity.setReturnDate(null);
        returnRequestEntity.setReturnRequestId(pickupRequestEntity.getPickupRequestId());
        returnRequestEntity.setDescription(pickupRequestEntity.getDescription());
        returnRequestEntity.setHubId(pickupRequestEntity.getHubId());
        returnRequestEntity.setLabelingtext(pickupRequestEntity.getLabelingtext());
        returnRequestEntity.setPaymentmethod(pickupRequestEntity.getPaymentmethod());
        returnRequestEntity.setPaymenttype(pickupRequestEntity.getPaymenttype());
        returnRequestEntity.setPickupdate(pickupRequestEntity.getPickupdate());
        returnRequestEntity.setPickupformatedaddress(pickupRequestEntity.getPickupformatedaddress());
        returnRequestEntity.setPickuplatitude(pickupRequestEntity.getPickuplatitude());
        returnRequestEntity.setPickuplongitude(pickupRequestEntity.getPickuplongitude());
        returnRequestEntity.setPickupwasellocation(pickupRequestEntity.getPickupwasellocation());
        returnRequestEntity.setRecipientadditionalinfo(deliveryRequestEntity.getRecipientadditionalinfo());
        returnRequestEntity.setRecipientformatedaddress(deliveryRequestEntity.getRecipientformatedaddress());
        returnRequestEntity.setRecipientId(deliveryRequestEntity.getRecipientId());
        returnRequestEntity.setRecipientlatitude(deliveryRequestEntity.getRecipientlatitude());
        returnRequestEntity.setRecipientlongitude(deliveryRequestEntity.getRecipientlongitude());
        returnRequestEntity.setRecipientmobile(deliveryRequestEntity.getRecipientmobile());
        returnRequestEntity.setRecipientname(deliveryRequestEntity.getRecipientname());
        returnRequestEntity.setRecipientwasellocation(deliveryRequestEntity.getRecipientwasellocation());
        returnRequestEntity.setRequesterId(pickupRequestEntity.getRequesterId());
        returnRequestEntity.setRequestStatus(null);
        returnRequestEntity.setRequesttime(pickupRequestEntity.getRequesttime());
        returnRequestEntity.setTimeFrom(pickupRequestEntity.getTimeFrom());
        returnRequestEntity.setTimeTo(pickupRequestEntity.getTimeTo());
        returnRequestEntity.setReturnDate(null);
        returnRequestEntity.setReturnFormattedAddress(pickupRequestEntity.getPickupformatedaddress());
        returnRequestEntity.setReturnLatitude(pickupRequestEntity.getPickuplatitude());
        returnRequestEntity.setReturnLongitude(pickupRequestEntity.getPickuplongitude());

        returnRequestEntity.setVersion(0L);

        return returnRequestEntity;
    }

    public DriverNotificationModel getDriverDetails(Long packageId, Long driverId) {

        DriverNotificationModel driverNotificationModel = new DriverNotificationModel();

        Long returnId = requestHistoryRepository.getPackageReturnId(packageId);
        ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(returnId);
        UsersEntity userEntity = usersRepository.findOne(driverId);
        ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.getActiveVehicleForDriverId(driverId);

        driverNotificationModel.setRequestId(returnId);
        driverNotificationModel.setHubId(returnRequestEntity.getHubId());
        driverNotificationModel.setPickupLatitude(returnRequestEntity.getReturnLatitude());
        driverNotificationModel.setPickupLongitude(returnRequestEntity.getReturnLongitude());

        driverNotificationModel.setDriverId(driverId);
        driverNotificationModel.setDriverName(userEntity.getUsername());
        driverNotificationModel.setDriverImageId(userEntity.getImageId());
        driverNotificationModel.setDriverPhoneNumber(activeVehicleEntity.getDevices().getPhonenumber());
        if (activeVehicleEntity.getDriver().getRating() != null) {
            driverNotificationModel.setDriverRating(activeVehicleEntity.getDriver().getRating().longValue());
        }
        driverNotificationModel.setVehicleId(activeVehicleEntity.getVehicle().getVehicleId());
        driverNotificationModel.setVehicleModel(activeVehicleEntity.getVehicle().getModel());
        driverNotificationModel.setVehiclePlateNumber(activeVehicleEntity.getVehicle().getPlate());

        driverNotificationModel.setRequestType("RETURN");

        return driverNotificationModel;
    }

    public Long getReceipentForPackage(Long packageId) {

        Long returnId = requestHistoryRepository.getPackageReturnId(packageId);
        ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(returnId);
        Long receipentId = returnRequestEntity.getRequesterId();

        return receipentId;
    }

    public ReturnRequestEntity getReturnForPackageId(Long packageId) {
        Long returnId = requestHistoryRepository.getPackageReturnId(packageId);
        ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(returnId);
        return returnRequestEntity;
    }

    public void changeAllDeliveryRequestsToActionNeeded(Long driverId) {

        ActiveVehicleModel activeVehicleModel = activeVehicleCouchbaseRepository.gettingActiveVehicleModel(driverId);
        // CONVERT NOT DELIVERED TO ACTION NEEDED
        for (JobOrder jobOrder : activeVehicleModel.getJobOrders()) {
            if ((!ReturnStatus.RETURNED.name().equals(jobOrder.getOrderStatus()))
                    || (!ReturnStatus.ACTION_NEEDED.name().equals(jobOrder.getOrderStatus()))) {
                // DELETE FROM COUCHBASE
                activeVehicleCouchbaseRepository.changeRequestStatus(driverId, jobOrder.getJobOrderId(),
                        ReturnStatus.ACTION_NEEDED.name());
                // CHANGE STATUS
                Long packageId = requestHistoryRepository.getDeliveryPackageId(jobOrder.getJobOrderId());
                if (packageId != null) {
                    ReturnPackageStatus returnPackageStatus = new ReturnPackageStatus();
                    returnPackageStatus.setPackageId(packageId);
                    returnPackageStatus.setPackageStatus(ReturnStatus.ACTION_NEEDED.name());
                    changePackageStatus(returnPackageStatus);
                }
            }
        }
    }

    public boolean changePackageStatus(ReturnPackageStatus returnPackageStatus) {
        Long returnId = requestHistoryRepository.getPackageReturnId(returnPackageStatus.getPackageId());
        Long packageId = returnPackageStatus.getPackageId();
        ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(returnId);
        returnRequestEntity.setRequestStatus(returnPackageStatus.getPackageStatus());
        // Save Return
        returnRequestRepository.save(returnRequestEntity);

        PackageEntity packageEntity = packageRepository.findOne(packageId);
        if (packageEntity != null) {
            packageEntity.setStatus(returnPackageStatus.getPackageStatus());
            packageRepository.save(packageEntity);
        }

        RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
        requestHistoryEntity.setRequestHistoryId(Utils.generateUUID());
        requestHistoryEntity.setRequestType("RETURN");
        requestHistoryEntity.setCreated(new Date());
        requestHistoryEntity.setRequestStatus(returnPackageStatus.getPackageStatus());
        requestHistoryEntity.setPackageId(packageId);
        requestHistoryEntity.setRequestId(returnRequestEntity.getReturnRequestId());
        requestHistoryRepository.save(requestHistoryEntity);

        PlanOrderEntity planOrderEntity = planOrdersRepository.findByReturnId(requestHistoryEntity.getRequestId());
        if (planOrderEntity != null) {
//            WorkflowPackageStatusCouchbase workflowPackageStatusCouchbase = new WorkflowPackageStatusCouchbase();
//            workflowPackageStatusCouchbase.setRequestId(requestHistoryEntity.getRequestId());
//            workflowPackageStatusCouchbase.setStatus(returnPackageStatus.getPackageStatus());
//            workflowPackageStatusCouchbase.setDriverId(planOrderEntity.getActiveVehicle().getDriver().getId());

            activeVehicleCouchbaseRepository.changeRequestStatus(planOrderEntity.getActiveVehicle().getDriver().getId(), requestHistoryEntity.getRequestId(), returnPackageStatus.getPackageStatus());
        }
        return true;
    }

}
