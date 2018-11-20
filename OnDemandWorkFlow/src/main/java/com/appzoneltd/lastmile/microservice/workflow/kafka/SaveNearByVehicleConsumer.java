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
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.functions.Action;

@Component
public class SaveNearByVehicleConsumer {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private RuntimeService runTimeService;
	
	@KafkaListener(topics = "SaveNearByVehicleResponse")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		WorkflowNearByVehicles workflowNearByVehicles= mapper.readValue(payload, WorkflowNearByVehicles.class);
		
		ProcessServicesHandler.get(workflowNearByVehicles.getPackageId(),"WaitForVehiclesToBeSaved").serviceConsumed(saveNearByVehicle(workflowNearByVehicles));
		
	}
	
		private Action saveNearByVehicle(final WorkflowNearByVehicles workflowNearByVehicles){
		    	
				return new Action(){
				
							@Override
							public void run() throws Exception {
		
								Map<String, Object> workflowData= new HashMap<String, Object>();
								workflowData.put("workflowNearByVehicles", workflowNearByVehicles);
								
								ProcessInstance processInstance=null;
						        List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery().
						                    variableValueEquals("packageId", workflowNearByVehicles.getPackageId()).list();
						        
						        if (processInstances != null && !processInstances.isEmpty()){
						        	processInstance = processInstances.get(0);
					            }
								
								Execution execution= runTimeService.createExecutionQuery()
												.processInstanceId(processInstance.getProcessInstanceId())
												.activityId("WaitForVehiclesToBeSaved").singleResult();
																	
								runTimeService.signal(execution.getId(),workflowData);
								
							}		    		
				 };
		}
	
	
}
