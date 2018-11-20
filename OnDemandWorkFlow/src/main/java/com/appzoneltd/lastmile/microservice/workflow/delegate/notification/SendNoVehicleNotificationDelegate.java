package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.business.dao.RequestHistoryRepo;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.service.OnDemandNotificationService;

@Service
public class SendNoVehicleNotificationDelegate implements JavaDelegate{

	@Autowired
	private OnDemandNotificationService notificationService;
	
	@Autowired
    private RequestHistoryRepo requestHistoryJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Getting WorkFlowNearByVehicle
		Long packageId=(Long) execution.getVariable("packageId");
		WorkflowNearByVehicles workflowNearByVehicles= (WorkflowNearByVehicles)execution.getVariable("workflowNearByVehicles");
		
		Long requestId=requestHistoryJpaRepository.getPackagePickupId(packageId);
		
		WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
		workflowNotificationModel.setCustomerId(workflowNearByVehicles.getRequesterId());
		workflowNotificationModel.setPackageId(packageId);
		workflowNotificationModel.setRequestId(requestId);
		// Send Notification0
		notificationService.sendNoVehicleFoundNotification(workflowNotificationModel);
	}

	
}
