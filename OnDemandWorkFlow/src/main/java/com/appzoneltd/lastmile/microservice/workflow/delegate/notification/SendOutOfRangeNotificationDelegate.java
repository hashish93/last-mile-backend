package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.business.dao.RequestHistoryRepo;
import com.appzoneltd.lastmile.microservice.workflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.service.OnDemandNotificationService;

@Service
public class SendOutOfRangeNotificationDelegate implements JavaDelegate{

	@Autowired
	private OnDemandNotificationService notificationService;
	
	@Autowired
    private RequestHistoryRepo requestHistoryJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" Sending Notification Out of Range ");
		Long packageId=(Long) execution.getVariable("packageId");
		Long customerId=(Long) execution.getVariable("requesterId");
		
		Long requestId=requestHistoryJpaRepository.getPackagePickupId(packageId);
		
		WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
		workflowNotificationModel.setCustomerId(customerId);
		workflowNotificationModel.setPackageId(packageId);
		workflowNotificationModel.setRequestId(requestId);
		// Sending Notification
		notificationService.sendOutOfRangeNotification(workflowNotificationModel);
	}

	
}
