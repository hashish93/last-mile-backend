package com.appzoneltd.lastmile.microservice.workflow.delegate;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;

@Service
public class AssignOrderToNearestDriverDelegate implements JavaDelegate{

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private WorkFlowProducer workFlowProducer;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		WorkflowNearByVehicles  workflowNearByVehicles=(WorkflowNearByVehicles) execution.getVariable("workflowNearByVehicles");
		// Automatic Dispatching Mode
		System.out.println(">>>>>>>>>>>>>>> SendPushNotificationToASSIGNED USER "+workflowNearByVehicles.toString());
		/// Create New Assignment List
		List<Long> assignedDrivers=new ArrayList<Long>();
		/// Assigned Driver
		assignedDrivers.add(workflowNearByVehicles.getVehicles().get(0));

		workflowNearByVehicles.setVehicles(assignedDrivers); 
		
	}//end execute
	
	
}
