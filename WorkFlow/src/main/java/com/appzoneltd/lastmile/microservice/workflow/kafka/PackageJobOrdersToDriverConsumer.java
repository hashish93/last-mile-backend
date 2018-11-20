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
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageOrderInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PackageJobOrdersToDriverConsumer {
	
	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "AssignTheOrderToDriverResponse")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		// Gettting From Kafka to Complete the Flow 
		WorkflowPackageOrderInfo workflowPackageOrderInfo= mapper.readValue(payload, WorkflowPackageOrderInfo.class);
		
		System.out.println("HHHHHHHHHHHHHHHHH%%%%%%%%%%%%%%%%%%%%%%55555555555555555555555555555555");
		System.out.println(">> WorkflowPackageOrderInfo : "+workflowPackageOrderInfo.toString());
		
		Map<String, Object> workflowData= new HashMap<String, Object>();
    	workflowData.put("packageId", workflowPackageOrderInfo.getPackageId());
    	workflowData.put("userId", workflowPackageOrderInfo.getCustomerId());
		
    	// Getting Process Instance Algorithm with PackageId
    	ProcessInstance processInstance = null;
		int timer=0;
		// Wait Until Data Persisted
		while(timer<10000){
			// Query the Process Instance 
			processInstance = runTimeService.createProcessInstanceQuery()
					.variableValueEquals("packageId", workflowPackageOrderInfo.getPackageId()).singleResult();
			if(processInstance!=null){
				break;
			}else{
				Thread.sleep(100L);
				timer+=100;
			}//end if-Else Block
		}//end while	
    
		System.out.println("%%%%%%%%%%%%%%%%%%%%% PROCESS INSTANCE : "+processInstance.toString());
						// Complete the Execution of the WorkFlow
						Execution execution=null;
						timer=0;
							while(timer<10000){
								// Query the Process Instance 
								execution = runTimeService.createExecutionQuery()
										.processInstanceId(processInstance.getProcessInstanceId()).activityId("waitUntilTheJobOrderAssignedtoDriver").singleResult();
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
