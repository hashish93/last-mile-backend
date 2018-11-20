package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SendNoVehicleNotificationDelegate implements JavaDelegate{

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private ObjectMapper mapper ;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// Getting WorkFlowNearByVehicle
		Long packageId=(Long) execution.getVariable("packageId");
		
		WorkflowNearByVehicles workflowNearByVehicles= (WorkflowNearByVehicles)execution.getVariable("workflowNearByVehicles");
		// Send Notification0
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> WORKFLOWNEAR BYH VEHICLESSSSSSSSSSS "+workflowNearByVehicles.toString());
		notificationService.sendNoVehicleFoundNotification(workflowNearByVehicles.getRequesterId(), packageId);
	}

	
}
