package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.OnDemandWorkflowModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OnDemandWorkflowConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@Transactional
	@KafkaListener(topics = "ONDEMAND_WORKFLOW_START")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {

		OnDemandWorkflowModel onDemandWorkflowModel = mapper.readValue(payload, OnDemandWorkflowModel.class);

		MyPrinter.print("OnDemandWorkflowConsumer ", onDemandWorkflowModel.toString());

		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", onDemandWorkflowModel.getPackageId());
		workflowData.put("requestId", onDemandWorkflowModel.getRequestId());
		workflowData.put("requestType", onDemandWorkflowModel.getRequestType());

		MyPrinter.workflowStep("1- Starting WorkFlow");

		if (onDemandWorkflowModel.getRequestType().equalsIgnoreCase("SCHEDULED")) {
			runTimeService.startProcessInstanceByKey("ScheduledPickupRequestProcess", workflowData);
		} else if (onDemandWorkflowModel.getRequestType().equalsIgnoreCase("ON-DEMAND")) {
			runTimeService.startProcessInstanceByKey("OndemandWorkflowProcess", workflowData);
		}

	}

}
