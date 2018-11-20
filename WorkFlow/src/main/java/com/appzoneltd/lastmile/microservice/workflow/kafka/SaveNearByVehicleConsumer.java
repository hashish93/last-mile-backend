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

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SaveNearByVehicleConsumer {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private RuntimeService runTimeService;
	
	@KafkaListener(topics = "SaveNearByVehicleResponse")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		//Getting NearBy Vehicles
		WorkflowNearByVehicles workflowNearByVehicles= mapper.readValue(payload, WorkflowNearByVehicles.class);
		Map<String, Object> workflowData= new HashMap<String, Object>();
		workflowData.put("workflowNearByVehicles", workflowNearByVehicles);
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%% Completin the Workflow and Persist the Results");
		// Getting Process Instance according to PackageID
		// Getting Process Instance Algorithm with PackageId
    	List<ProcessInstance> processInstances = null;
    	ProcessInstance processInstance=null;
		int timer=0;
		// Wait Until Data Persisted
		while(timer<10000){
			// Query the Process Instance 
			processInstances = runTimeService.createProcessInstanceQuery()
					.variableValueEquals("packageId", workflowNearByVehicles.getPackageId()).list();
			if(!processInstances.isEmpty()){
				processInstance=processInstances.get(0);
				break;
			}else{
				Thread.sleep(100L);
				timer+=100;
			}//end if-Else Block
		}//end while	
		
		// Complete the Execution of the WorkFlow
		List<Execution> executions=null;
		Execution execution=null;
		timer=0;
			while(timer<10000){
				// Query the Process Instance 
				executions = runTimeService.createExecutionQuery()
						.processInstanceId(processInstance.getProcessInstanceId()).activityId("WaitForVehiclesToBeSaved").list();
				if(!executions.isEmpty()){
					execution=executions.get(0);
					break;
				}else{
					Thread.sleep(100L);
					timer+=100;
				}//end if-Else Block
			}//end while
		// Send Signal 
		runTimeService.signal(execution.getId(),workflowData);
		
	}
	
}
