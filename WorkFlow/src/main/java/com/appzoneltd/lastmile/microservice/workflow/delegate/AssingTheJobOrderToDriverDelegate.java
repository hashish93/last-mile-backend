package com.appzoneltd.lastmile.microservice.workflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowDriverResponse;
import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AssingTheJobOrderToDriverDelegate implements JavaDelegate{

	@Autowired
	private ObjectMapper mapper ;
	
	@Autowired
	private WorkFlowProducer workFlowProducer;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		// Getting PackageId to Process it 
		Long packageId=(Long) execution.getVariable("packageId");
		Long driverId=(Long) execution.getVariable("driverId");
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ASSINGING THE JOB ORDER TO THE DRIVER  ");
		WorkflowDriverResponse workflowDriverResponse=new WorkflowDriverResponse();
		workflowDriverResponse.setPackageId(packageId);
		workflowDriverResponse.setDriverId(driverId);
		/// Sending ChangePackageStatusRequest 
		workFlowProducer.sendMessage("AssignTheOrderToDriver", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowDriverResponse));
		
	}//end execute
	
	
	
}
