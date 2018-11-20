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
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowTrackingNumber;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.functions.Action;

@Component
public class PackageTrackingConsumer {
	
	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "PackageTrackingNumberResponse")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		WorkflowTrackingNumber workflowTrackingNumber=mapper.readValue(payload, WorkflowTrackingNumber.class);
		
		ProcessServicesHandler.get(workflowTrackingNumber.getPackageId(),"waitUntilTrackingNumberAssigned").serviceConsumed(sendTrackingNumber(workflowTrackingNumber));
		
	}
	
	private Action sendTrackingNumber(final WorkflowTrackingNumber workflowTrackingNumber){
    	
		return new Action(){
		
					@Override
					public void run() throws Exception {

						Map<String, Object> workflowData= new HashMap<String, Object>();
				    	workflowData.put("packageId", workflowTrackingNumber.getPackageId());
				    	workflowData.put("trackingNumber", workflowTrackingNumber.getTrackingNumber());
				    	
						System.out.println("***************************************************");
						System.out.println("*******TRACKING NUMBER *********");
						System.out.println("***************************************************");		

						ProcessInstance processInstance=null;
				        List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery().
				                    variableValueEquals("packageId", workflowTrackingNumber.getPackageId()).list();
				        
				        if (processInstances != null && !processInstances.isEmpty()){
				        	processInstance = processInstances.get(0);
			            }

						Execution execution=runTimeService.createExecutionQuery()
										.processInstanceId(processInstance.getProcessInstanceId())
										.activityId("waitUntilTrackingNumberAssigned").singleResult();
							
						runTimeService.signal(execution.getId(),workflowData);	
					}
		    		
		    	};
	}
	
}
