package com.appzoneltd.lastmile.microservice.workflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SaveNearbyVehiclesforPackageDelegate implements JavaDelegate{

	@Autowired
	private WorkFlowProducer workFlowProducer;
	
	@Autowired
	private ObjectMapper mapper ;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		WorkflowNearByVehicles  workflowNearByVehicles=(WorkflowNearByVehicles) execution.getVariable("workflowNearByVehicles");
		// SAVING NEAR BY VEHICLES
		Long pacakgeId=(Long) execution.getVariable("packageId");
		System.out.println(">>>>>>>>>>>>>>> SAVING NEARBY VEHICLES FOR THE PACKAGE "+workflowNearByVehicles.toString());
		workflowNearByVehicles.setPackageId(pacakgeId);
		// Sending the Save Order to Service
		workFlowProducer.sendMessage("SaveNearByVehicleRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowNearByVehicles));
		
	}//end execute
	
}
