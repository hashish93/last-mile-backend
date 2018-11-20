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
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowDriverResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.functions.Action;

@Component
public class DriverResponseConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "DriverResponseToRequest")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {

		// Getting Driver Action
		WorkflowDriverResponse workflowDriverResponse = mapper.readValue(payload, WorkflowDriverResponse.class);

		if (workflowDriverResponse.isGoExtra()) {
			Map<String, Object> workflowData = new HashMap<String, Object>();
			workflowData.put("packageId", workflowDriverResponse.getPackageId());
			workflowData.put("driverId", workflowDriverResponse.getDriverId());
			workflowData.put("requestId", workflowDriverResponse.getRequestId());
			workflowData.put("userId", workflowDriverResponse.getRequesterId());
			if (workflowDriverResponse.isResponse()) {
				System.out.println("%%%%%%%%%%%%%%%%%%%% DRIVER ACCEPT GO EXTRA " + workflowDriverResponse.getDriverId()
						+ " ACCEPT THE REQUEST");
				workflowData.put("driverResponse", true);
				runTimeService.startProcessInstanceByMessage("startInGoExtraCase",workflowData);
			}
		} else {
			ProcessServicesHandler.get(workflowDriverResponse.getPackageId(), "waitForDriverResponse")
					.serviceConsumed(driverResponse(workflowDriverResponse));
		}
	}

	private Action driverResponse(final WorkflowDriverResponse workflowDriverResponse) {
		return new Action() {
			@Override
			public void run() throws Exception {
				/// generate Map to Complete Flow
				executeGoExtra(workflowDriverResponse);
			}
		};
	}

	private void executeGoExtra(WorkflowDriverResponse workflowDriverResponse) {

		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", workflowDriverResponse.getPackageId());
		workflowData.put("driverId", workflowDriverResponse.getDriverId());
		System.out.println("***************************************************");
		System.out.println("*******DRIVER RESPONSE TO ONDEMAND REQUEST ssssss*********");
		System.out.println("******* DRIVER ID " + workflowDriverResponse.getDriverId());
		System.out.println("******* RESPONSE " + workflowDriverResponse.isResponse());
		System.out.println("***************************************************");

		if (workflowDriverResponse.isResponse()) {
			//
			System.out.println("%%%%%%%%%%%%%%%%%%%% HOLA THE DRIVER " + workflowDriverResponse.getDriverId()
					+ " ACCEPT THE REQUEST");
			workflowData.put("driverResponse", true);
		} else {
			System.out.println("%%%%%%%%%%%%%%%%%%%% HOLA ALL DRIVER REJECT THE REQUEST");
			workflowData.put("driverResponse", false);
		}
		///////////////////////////////////////////////////////////////////
			ProcessInstance processInstance = null;
			List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery()
					.variableValueEquals("packageId", workflowDriverResponse.getPackageId()).list();
	
			if (processInstances != null && !processInstances.isEmpty()) {
				processInstance = processInstances.get(0);
			}
	
			Execution execution = runTimeService.createExecutionQuery()
					.processInstanceId(processInstance.getProcessInstanceId()).activityId("waitForDriverResponse")
					.singleResult();
	
			runTimeService.signal(execution.getId(), workflowData);
	}

}
