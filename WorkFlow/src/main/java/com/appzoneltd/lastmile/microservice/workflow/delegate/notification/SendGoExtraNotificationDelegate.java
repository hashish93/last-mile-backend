package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;


import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service	
public class SendGoExtraNotificationDelegate implements JavaDelegate {

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private ObjectMapper mapper ;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		WorkflowNearByVehicles workflowNearByVehicles=(WorkflowNearByVehicles) execution.getVariable("workflowNearByVehicles");
		// Send Notification
		notificationService.sendGoExtraNotification(workflowNearByVehicles);
		// Adding PackageId to process 
		Map<String, Object> workflowData= new HashMap<String, Object>();
    	workflowData.put("packageId", workflowNearByVehicles.getPackageId());
    	
    	/// Starting Waiting For Driver Response Message 
    	//runtimeService.startProcessInstanceByMessage("startDriverResponse",workflowData);
	}//end Execute 
	
}
