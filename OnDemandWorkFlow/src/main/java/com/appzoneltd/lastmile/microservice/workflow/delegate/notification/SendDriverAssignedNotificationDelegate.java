package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.business.dao.RequestHistoryRepo;
import com.appzoneltd.lastmile.microservice.workflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.service.OnDemandNotificationService;

@Service
public class SendDriverAssignedNotificationDelegate implements JavaDelegate{

	@Autowired
	private OnDemandNotificationService notificationService;
	
	@Autowired
    private RequestHistoryRepo requestHistoryJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// Getting PackageId to Process it 
		Long packageId=(Long) execution.getVariable("packageId");
		Long userId=(Long) execution.getVariable("userId");
		Long requestId=requestHistoryJpaRepository.getPackagePickupId(packageId);
		
		WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
		workflowNotificationModel.setCustomerId(userId);
		workflowNotificationModel.setPackageId(packageId);
		workflowNotificationModel.setRequestId(requestId);
		// Send Notification
		notificationService.sendDriverAssignedNotification(workflowNotificationModel);
		
	}//end Execute 
	
}
