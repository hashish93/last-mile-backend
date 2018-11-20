package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.business.dao.RequestHistoryRepo;
import com.appzoneltd.lastmile.microservice.workflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.service.OnDemandNotificationService;

@Service
public class SendRatingServiceNotificationDelegate implements JavaDelegate{

	@Autowired
	private OnDemandNotificationService notificationService;
	
	@Autowired
    private RequestHistoryRepo requestHistoryJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" SendRatingServiceNotificationDelegate ");
		Long packageId=(Long) execution.getVariable("packageId");
		Long driverId=(Long) execution.getVariable("driverId");
		Long customerId=(Long) execution.getVariable("requesterId");
		Thread.sleep(100);
		
		Long requestId=requestHistoryJpaRepository.getPackagePickupId(packageId);
		
		WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
		workflowNotificationModel.setCustomerId(customerId);
		workflowNotificationModel.setDriverId(driverId);
		workflowNotificationModel.setPackageId(packageId);
		workflowNotificationModel.setRequestId(requestId);
		
		notificationService.sendRatingServiceNotification(workflowNotificationModel);
		
	}


}
