package com.appzoneltd.lastmile.microservice.workflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowBase;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NearByVehicleDelegate implements JavaDelegate{

	@Autowired
	private WorkFlowProducer workFlowProducer;
	
	@Autowired
	private ObjectMapper mapper ;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long packageId=(Long) execution.getVariable("packageId");		
		// Init WorkflowBase 
		WorkflowBase workflowBase=new WorkflowBase();
		workflowBase.setPackageId(packageId);
		System.out.println(">>>> NEAR BY VEHICLE SERVICE");
		/// push to WorkFlow Service 
		workFlowProducer.sendMessage("NearByVehicleRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));
		
	}
	
}
