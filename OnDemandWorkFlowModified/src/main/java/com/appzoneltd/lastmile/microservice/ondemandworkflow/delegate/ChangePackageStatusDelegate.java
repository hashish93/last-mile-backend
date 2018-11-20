package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.CancelationReasonJpaRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PlanOrderJpaRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.RequestCancelationRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.CancelationReasonEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.RequestCancelationEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.NearByVehicleEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.PackageStatusModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowDao.NearByVehicleRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.DriverNotificationService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.JobOrdersService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.RequestService;

@Service
public class ChangePackageStatusDelegate implements JavaDelegate {

    @Autowired
    private RequestService requestService;

    @Autowired
    private JobOrdersService jobOrdersService;

    @Autowired
    private NearByVehicleRepository nearByVehicleRepository;

    @Autowired
    private RequestHistoryRepository requestHistoryJpaRepository;

    @Autowired
    private RequestCancelationRepository requestCancelationRepository;

    @Autowired
    private PickupRequestRepository pickupRequestRepository;

    @Autowired
    private CancelationReasonJpaRepository cancelationReasonJpaRepository;

    @Autowired
    private DriverNotificationService driverNotificationService;
    
    @Autowired
    private PlanOrderJpaRepository planOrderRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long requestId = (Long) execution.getVariable("requestId");
        Long packageId = (Long) execution.getVariable("packageId");
        Long driverId = null;
        PackageStatusModel packageStatusModel = new PackageStatusModel();

        List<NearByVehicleEntity> nearByVehicleEntities = (List<NearByVehicleEntity>) nearByVehicleRepository
                .findAcceptedVehiclesForPackage(packageId);
        if ((nearByVehicleEntities != null) && (nearByVehicleEntities.size() > 0)) {
            // driver id not vehicle id
            driverId = nearByVehicleEntities.get(0).getActiveVehicleId();
        }
        if (requestId == null && packageId != null) {
            requestId = requestHistoryJpaRepository.getRequestIdFromPackageId(packageId);
        }
        if (packageId == null && requestId != null) {
            packageId = requestHistoryJpaRepository.getPackageId(requestId);
        }

        packageStatusModel.setPackageId(packageId);
        packageStatusModel.setRequestId(requestId);

        String activityName = execution.getCurrentActivityName();

        if (activityName != null) {
            String packageStatus = activityName.substring(activityName.indexOf('"') + 1, activityName.lastIndexOf('"'))
                    .trim();
            MyPrinter.workflowStep("2- ChangePackageStatus TO " + packageStatus);
            if (packageStatus.equals("New")) {
                packageStatusModel.setStatus(ChangePackageStatusEnum.NEW.getPackageStatus());
            } else if (packageStatus.equals("Waiting for Customer Alternative")) {
                packageStatusModel
                        .setStatus(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE.getPackageStatus());
            } else if (packageStatus.equals("Awaiting Pickup")) {
                packageStatusModel.setStatus(ChangePackageStatusEnum.AWAITING_PICKUP.getPackageStatus());
                changePackageStatusInCouchBase(driverId, requestId, packageStatusModel.getStatus());
            } else if (packageStatus.equals("Action Needed")) {
                packageStatusModel.setStatus(ChangePackageStatusEnum.ACTION_NEEDED.getPackageStatus());
            } else if (packageStatus.equals("Pickup verification")) {
                packageStatusModel.setStatus(ChangePackageStatusEnum.IN_PICKUP_VERIFICATION.getPackageStatus());
                changePackageStatusInCouchBase(driverId, requestId, packageStatusModel.getStatus());
            } else if (packageStatus.equals("Picked up")) {
            	changePlanOrderToExcluded(requestId);
                packageStatusModel.setStatus(ChangePackageStatusEnum.PICKEDUP.getPackageStatus());
                changePackageStatusInCouchBase(driverId, requestId, packageStatusModel.getStatus());
            } else if (packageStatus.equals("Cancelled")) {
                packageStatusModel.setStatus(ChangePackageStatusEnum.CANCELED.getPackageStatus());
                changePlanOrderToExcluded(requestId);
                boolean isDriver = execution.getVariable("isDriver") != null
                        ? (Boolean) execution.getVariable("isDriver") : false;
                Long reasonId = execution.getVariable("reasonId") != null ? (Long) execution.getVariable("reasonId")
                        : null;
                String description = execution.getVariable("description") != null ? (String) execution.getVariable("description")
                        : null;

                RequestCancelationEntity cancelationEntity = new RequestCancelationEntity();
                cancelationEntity.setReasonId(reasonId);
                cancelationEntity.setRequestId(requestId);
                cancelationEntity.setDescription(description);

                CancelationReasonEntity cancelationReasonEntity = cancelationReasonJpaRepository.findOne(reasonId);
                PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
                pickupRequestEntity.setCancellationReason(cancelationReasonEntity.getReason());

                pickupRequestRepository.save(pickupRequestEntity);
                requestCancelationRepository.save(cancelationEntity);

                if (!isDriver){
                	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> if not driver");
                	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> driverId "+driverId);
                	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> requestId "+requestId);
                	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> packageId "+packageId);
                	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> if not driver");
                	driverNotificationService.sendDriverNotification(requestId, packageId, driverId);
                }
                    

                jobOrdersService.deleteCancelledRequest(requestId);
            } else if (packageStatus.equalsIgnoreCase("Scheduled for pickup")) {
                packageStatusModel.setStatus(ChangePackageStatusEnum.SCHEDULED_FOR_PICKUP.getPackageStatus());
            } else if (packageStatus.equals("Assigned")) {
                packageStatusModel.setStatus(ChangePackageStatusEnum.ASSIGNED.getPackageStatus());
            }
            requestService.changePackageStatus(packageStatusModel);
        }
    }

    private void changePlanOrderToExcluded(Long requestId){
    	PlanOrderEntity planOrderEntity = planOrderRepository.findByPickupId(requestId);
		if (planOrderEntity != null) {
			planOrderEntity.setExcluded(true);
			planOrderRepository.save(planOrderEntity);

		}
    }
    private void changePackageStatusInCouchBase(Long driverId, Long requestId, String status) {
        if (driverId != null) {
            jobOrdersService.changeRequestStatus(driverId, requestId, status);
        }

    }

}
