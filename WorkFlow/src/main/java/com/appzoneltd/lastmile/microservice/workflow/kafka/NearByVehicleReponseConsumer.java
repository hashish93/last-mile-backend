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

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Component
public class NearByVehicleReponseConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "NearByVehicleResponse")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		/// Receiving Payload from Kafka
		WorkflowNearByVehicles workflowNearByVehicles = mapper.readValue(payload, WorkflowNearByVehicles.class);
		// Viewing Data

		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("vehicleFound", (workflowNearByVehicles.getVehicles().isEmpty()) ? false : true);
		workflowData.put("automatic", workflowNearByVehicles.isAutomatic());
		workflowData.put("workflowNearByVehicles", workflowNearByVehicles);

		System.out.println(
				"%%%%%%%%%%%%%%%%%%%%% WARNING ,, WE ARE ABOUT TO SEND REQUEST AND COMPLETE WORKFLOW TO SEND PUSH NOTIFICATION TO DRIVER");
		System.out.println("%%%%%%%%%%%%%%%%%%%%% RECEIVED : " + workflowNearByVehicles.getPackageId());
		System.out.println("%%%%%%%%%%%%%%%%%%%%% DATA : " + workflowData.toString());
		// Getting Process Instance according to PackageID
				///////////////////////////////////////////////////////////////////
				// Getting Process Instance Algorithm with PackageId
				List<ProcessInstance> processInstances=null;
				ProcessInstance processInstance=null;
				int times1=0;
				// While Send
				while(times1<30){
					// Sleep 1 Second
					Thread.sleep(1000L);
					// Getting Process Instances
					processInstances = runTimeService.createProcessInstanceQuery()
					.variableValueEquals("packageId", workflowNearByVehicles.getPackageId()).list();
					if(!processInstances.isEmpty()){
						processInstance=processInstances.get(0);
						break;
					}//end if
					times1++;
				}//end while
				
				// Getting Process Instance Algorithm with PackageId
				List<Execution> executions=null;
				Execution execution=null;
				int times2=0;
				// While Send
				while(times2<10){
					// Sleep 1 Second
					Thread.sleep(1000L);
					// Getting Process Instances
					executions= runTimeService.createExecutionQuery().processInstanceId(processInstance.getProcessInstanceId())
							.activityId("waitNearByVehicles").list();
					if(!executions.isEmpty()){
						execution=executions.get(0);
						break;
					}//end if 
					times2++;
				}//end while
				
				runTimeService.signal(execution.getId(), workflowData);
				

			}

		}