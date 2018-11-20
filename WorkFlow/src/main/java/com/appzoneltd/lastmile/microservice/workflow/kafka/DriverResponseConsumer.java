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

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowDriverResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DriverResponseConsumer {

	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "DriverResponseToRequest")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		// Getting Driver Action 
		WorkflowDriverResponse workflowDriverResponse= mapper.readValue(payload, WorkflowDriverResponse.class);
		
		Map<String, Object> workflowData= new HashMap<String, Object>();
    	workflowData.put("packageId", workflowDriverResponse.getPackageId());
    	workflowData.put("driverId", workflowDriverResponse.getDriverId());
    	
		System.out.println("***************************************************");
		System.out.println("*******DRIVER RESPONSE TO ONDEMAND REQUEST*********");
		System.out.println("***************************************************");		
		System.out.println("PAYLOAD TO COMPLETE WORKFLOW "+payload);
		
		if(workflowDriverResponse.isResponse()){
			// 
			System.out.println("%%%%%%%%%%%%%%%%%%%% HOLA THE DRIVER "+workflowDriverResponse.getDriverId()+" ACCEPT THE REQUEST");
			workflowData.put("driverResponse", true);			
		}else{			
			System.out.println("%%%%%%%%%%%%%%%%%%%% HOLA ALL DRIVER REJECT THE REQUEST");
			workflowData.put("driverResponse", false);
		}
		///////////////////////////////////////////////////////////////////
		// Getting Process Instance Algorithm with PackageId
    	List<ProcessInstance> processInstances = null;
    	ProcessInstance processInstance=null;
		int timer=0;
		// Wait Until Data Persisted
		while(timer<10000){
			// Query the Process Instance 
			processInstances = runTimeService.createProcessInstanceQuery()
					.variableValueEquals("packageId", workflowDriverResponse.getPackageId()).list();
			if(!processInstances.isEmpty()){
				processInstance=processInstances.get(0);
				break;
			}else{
				Thread.sleep(100L);
				timer+=100;
			}//end if-Else Block
		}//end while		 	
		////////////////////////////////////////////////////////
		System.out.println(">>> PROCESS INSTANCE RETURNED IN "+timer+" MS");
		// Getting Process Instance according to PackageID
				// Complete the Execution of the WorkFlow
				if(processInstance!=null){
						/// Start Execution 
					List<Execution> executions=null;
					Execution execution=null;
					timer=0;
						while(timer<10000){
							// Query the Process Instance 
							executions = runTimeService.createExecutionQuery()
									.processInstanceId(processInstance.getProcessInstanceId()).activityId("waitForDriverResponse").list();
							if(!executions.isEmpty()){
								execution=executions.get(0);
								break;
							}else{
								Thread.sleep(100L);
								timer+=100;
							}//end if-Else Block
						}//end while
						runTimeService.signal(execution.getId(),workflowData);
				}//end if Condition
				else{
					System.out.println("************ SORRY PROCESS HAS BEEN ENDED DUE TO TIMOUT");
				}//end else
	}
	
}
