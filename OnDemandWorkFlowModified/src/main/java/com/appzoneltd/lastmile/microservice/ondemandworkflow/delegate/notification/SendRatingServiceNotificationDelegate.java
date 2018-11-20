package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;

@Service
public class SendRatingServiceNotificationDelegate implements JavaDelegate{

	@Autowired
	private OnDemandNotificationService notificationService;
	
	@Autowired
	private PickupRequestRepository pickupRequestRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" SendRatingServiceNotificationDelegate ");
		Long packageId=(Long) execution.getVariable("packageId");
		Long requestId = (Long) execution.getVariable("requestId");
		Long driverId=(Long) execution.getVariable("driverId");
		
		Thread.sleep(100);
		
		if (requestId != null) {
			PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
			if (pickupRequestEntity != null) {
				Long requesterId = pickupRequestEntity.getRequesterId();
				WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
				workflowNotificationModel.setCustomerId(requesterId);
				workflowNotificationModel.setDriverId(driverId);
				workflowNotificationModel.setPackageId(packageId);
				workflowNotificationModel.setRequestId(requestId);
				MyPrinter.print("NOTIFICATION", "we are about to send notification with rating "+workflowNotificationModel);
				notificationService.sendRatingServiceNotification(workflowNotificationModel);
			}
		}
		
	
		
		
		
	}


}
