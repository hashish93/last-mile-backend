package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;

@Service
public class SendRequestCancelledNotificationDelegate implements JavaDelegate{

	@Autowired
	private OnDemandNotificationService notificationService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long packageId=(Long) execution.getVariable("packageId");
		Long requestId=(Long) execution.getVariable("requestId");
		Long requesterId=(Long) execution.getVariable("requesterId");
		WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
		workflowNotificationModel.setCustomerId(requesterId);
		workflowNotificationModel.setPackageId(packageId);
		workflowNotificationModel.setRequestId(requestId);
		MyPrinter.workflowStep("SEND CANCEL NOTIFICATION STEP");
		MyPrinter.print("Notification", "Notification send to customer to cancel "+workflowNotificationModel);
		notificationService.sendRequestCancelledNotification(workflowNotificationModel);
		
	}


}