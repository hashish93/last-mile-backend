package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowCancelRequest;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class CancelPickupRequestConsumer {

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private RuntimeService runTimeService;
	@Autowired
	private RequestHistoryRepository requestHistoryRepository;

	@KafkaListener(topics = "CancelPickupRequest")
	public void userCancelPickupRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		WorkflowCancelRequest workflowCancelRequest = mapper.readValue(payload, WorkflowCancelRequest.class);
		Long packageId = workflowCancelRequest.getPackageId();
		Long requestId = workflowCancelRequest.getRequestId();
		Long reasonId = workflowCancelRequest.getReasonId();
		
		Map<String, Object> workflowData = new HashMap<String, Object>();	
		workflowData.put("packageId", packageId);
		workflowData.put("requesterId", workflowCancelRequest.getRequesterId());
		
		workflowData.put("admin", workflowCancelRequest.isAdmin());
		workflowData.put("isDriver", workflowCancelRequest.getDriverId() != null);
		workflowData.put("driverId", workflowCancelRequest.getDriverId());
		workflowData.put("reasonId", reasonId);
		MyPrinter.print("CONSUMER","consumer data to start cancel "+workflowData.toString());
		if(packageId !=null){
			requestId = (requestId!=null) ? requestId : requestHistoryRepository.getRequestIdFromPackageId(packageId);
			workflowData.put("requestId",requestId);
			MyPrinter.print("Consumer Map", "this is about sending cancel data "+workflowData.toString());
			runTimeService.startProcessInstanceByMessage("startCancelRequestModule", workflowData);
		}
		
		
		
	}

}
