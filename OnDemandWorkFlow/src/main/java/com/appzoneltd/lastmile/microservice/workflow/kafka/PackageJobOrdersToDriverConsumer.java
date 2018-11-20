package com.appzoneltd.lastmile.microservice.workflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflow.handler.ProcessServicesHandler;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageOrderInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.functions.Action;

@Component
public class PackageJobOrdersToDriverConsumer {
	
	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "AssignTheOrderToDriverResponse")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
			WorkflowPackageOrderInfo workflowPackageOrderInfo= mapper.readValue(payload, WorkflowPackageOrderInfo.class);

			ProcessServicesHandler.get(workflowPackageOrderInfo.getPackageId(),"waitUntilTheJobOrderAssignedtoDriver").serviceConsumed(assignOrderToDriver(workflowPackageOrderInfo));
	}
	
	private Action assignOrderToDriver(final WorkflowPackageOrderInfo workflowPackageOrderInfo){
		    	
		return new Action(){
		
					@Override
					public void run() throws Exception {
						/// generate Map to Complete Flow
						Map<String, Object> workflowData= new HashMap<String, Object>();
				    	workflowData.put("packageId", workflowPackageOrderInfo.getPackageId());
				    	workflowData.put("userId", workflowPackageOrderInfo.getCustomerId());
						
				    	ProcessInstance processInstance=null;
				        List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery().
				                    variableValueEquals("packageId", workflowPackageOrderInfo.getPackageId()).list();
				        
				        if (processInstances != null && !processInstances.isEmpty()){
				        	processInstance = processInstances.get(0);
			            }
				        
				        Execution execution =runTimeService.createExecutionQuery()
								.processInstanceId(processInstance.getProcessInstanceId())
								.activityId("waitUntilTheJobOrderAssignedtoDriver").singleResult();
				    	
				        runTimeService.signal(execution.getId(),workflowData);	 		
					}
		    		
		    	};
	}
	
}
