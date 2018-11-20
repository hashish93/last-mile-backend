package com.appzoneltd.lastmile.microservice.workflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowTrackingNumber;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		Map<String, Object> workflowData= new HashMap<String, Object>();
    	workflowData.put("packageId", workflowTrackingNumber.getPackageId());
    	workflowData.put("trackingNumber", workflowTrackingNumber.getTrackingNumber());
    	
		System.out.println("***************************************************");
		System.out.println("*******TRACKING NUMBER *********");
		System.out.println("***************************************************");		
		System.out.println("PAYLOAD TO COMPLETE WORKFLOW "+payload);
		
		// Getting Process Instance according to PackageID
		// Getting Process Instance Algorithm with PackageId
    	ProcessInstance processInstance = null;
		int timer=0;
		// Wait Until Data Persisted
		while(timer<10000){
			// Query the Process Instance 
			processInstance = runTimeService.createProcessInstanceQuery()
					.variableValueEquals("packageId", workflowTrackingNumber.getPackageId()).singleResult();
			if(processInstance!=null){
				break;
			}else{
				Thread.sleep(100L);
				timer+=100;
			}//end if-Else Block
		}//end while	
		
		// Complete the Execution of the WorkFlow

		Execution execution=null;
		timer=0;
			while(timer<10000){
				// Query the Process Instance 
				execution = runTimeService.createExecutionQuery()
						.processInstanceId(processInstance.getProcessInstanceId()).activityId("waitUntilTrackingNumberAssigned").singleResult();
				if(execution!=null){
					break;
				}else{
					Thread.sleep(100L);
					timer+=100;
				}//end if-Else Block
			}//end while
		
					runTimeService.signal(execution.getId(),workflowData);
		
    	
		
	}
}
