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
public class SendDriverArrivedNotificationDelegate implements JavaDelegate{
	
	@Autowired
	private PickupRequestRepository pickupRequestRepository;
	
	@Autowired
	private OnDemandNotificationService notificationService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// 
		MyPrinter.workflowStep("step 3 push notification to customer as driver arrived");
		MyPrinter.print("NOTIFICATION","");
		Long packageId=(Long) execution.getVariable("packageId");
		Long requestId=(Long) execution.getVariable("requestId");
		Long requesterId = null;
		if(requestId !=null){
			PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
			if(pickupRequestEntity !=null){
				requesterId = pickupRequestEntity.getRequesterId();
				WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel(); 
				workflowNotificationModel.setCustomerId(requesterId);
				workflowNotificationModel.setPackageId(packageId);
				workflowNotificationModel.setRequestId(requestId);
				// Send Notification
				System.out.println("Notification send to user as driver arrived "+workflowNotificationModel.toString());
				notificationService.sendDriverArrivedNotification(workflowNotificationModel);
			}
		}
		
		
		
	}//end Execute 
	
}
