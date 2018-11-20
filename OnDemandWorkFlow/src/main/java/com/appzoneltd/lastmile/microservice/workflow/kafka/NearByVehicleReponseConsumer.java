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
public class NearByVehicleReponseConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "NearByVehicleResponse")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {

		WorkflowNearByVehicles workflowNearByVehicles = mapper.readValue(payload, WorkflowNearByVehicles.class);
		
		ProcessServicesHandler.get(workflowNearByVehicles.getPackageId(),"waitNearByVehicles").serviceConsumed(nearByVehicle(workflowNearByVehicles));

	}

	private Action nearByVehicle(final WorkflowNearByVehicles workflowNearByVehicles){
    	
    	return new Action(){

			@Override
			public void run() throws Exception {
				/// generate Map to Complete Flow
				Map<String, Object> workflowData = new HashMap<String, Object>();
				workflowData.put("vehicleFound", (workflowNearByVehicles.getVehicles().isEmpty()) ? false : true);
				workflowData.put("automatic", workflowNearByVehicles.isAutomatic());
				workflowData.put("workflowNearByVehicles", workflowNearByVehicles);

				System.out.println(
						"%%%%%%%%%%%%%%%%%%%%% WARNING ,, WE ARE ABOUT TO SEND REQUEST AND COMPLETE WORKFLOW TO SEND PUSH NOTIFICATION TO DRIVER");
				System.out.println("%%%%%%%%%%%%%%%%%%%%% RECEIVED : " + workflowNearByVehicles.getPackageId());
				System.out.println("%%%%%%%%%%%%%%%%%%%%% DATA : " + workflowData.toString());
				///////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////
				ProcessInstance processInstance=null;
				List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery().
				variableValueEquals("packageId", workflowNearByVehicles.getPackageId()).list();
				
				if (processInstances != null && !processInstances.isEmpty()){
					processInstance = processInstances.get(0);
				}
							
				Execution execution= runTimeService.createExecutionQuery().processInstanceId(processInstance.getProcessInstanceId())
									.activityId("waitNearByVehicles").singleResult();
				
				runTimeService.signal(execution.getId(), workflowData);
			}
    		
    	};
    }
	
}