package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class DriverNotificationService {

	@Autowired
	private PickupRequestRepository pickupRequestRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private OnDemandNotificationService onDemandNotificationService;
	
	public void sendDriverNotification(Long requestId , Long packageId, Long driverId) throws JsonProcessingException{
		WorkflowNotificationModel workflowNotificationModel = new WorkflowNotificationModel();
		if(requestId !=null){
			PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findByPickupRequestId(requestId);
			if(pickupRequestEntity !=null){
				workflowNotificationModel.setCustomerId(pickupRequestEntity.getRequesterId());
			}
		}
		if(packageId !=null){
			PackageEntity packageEntity = packageRepository.findOne(packageId);
			if(packageEntity !=null){
				workflowNotificationModel.setTrackingNumber(packageEntity.getTrackingNumber());
			}
		}
		workflowNotificationModel.setDriverId(driverId);
		workflowNotificationModel.setPackageId(packageId);
		workflowNotificationModel.setRequestId(requestId);
		onDemandNotificationService.sendRequestCancelledNotificationToDriver(workflowNotificationModel);
		
	}
}
