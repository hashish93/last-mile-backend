package com.appzoneltd.lastmile.microservice.workflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageStatusCouchbase;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPickupRequestInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DriverArrivalCosumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;
	
	@Autowired
	private WorkFlowProducer workFlowProducer;

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowConsumer.class);

	// private CountDownLatch latch = new CountDownLatch(1);

	@KafkaListener(topics = "DRIVER_ARRIVAL_ACKNOLEDGMENT")
	public void receiveMessage(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		/// WorkFlowPickup Request
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PAYLOAD "+payload);
		//
		
		WorkflowPickupRequestInfo workflowPickupRequestInfo= mapper.readValue(payload, WorkflowPickupRequestInfo.class);
		
		/// generate Map to Complete Flow	
		Map<String, Object> workflowData= new HashMap<String, Object>();
		workflowData.put("packageId", workflowPickupRequestInfo.getPackageId());
		workflowData.put("userId", workflowPickupRequestInfo.getRequesterId());
		
		System.out.println(">>>>>>>>>>>>>>>>>DRIVER_ARRIVAL_ACKNOLEDGMENT "+workflowPickupRequestInfo.toString());
		
		WorkflowPackageStatusCouchbase workflowPackageStatusCouchbase=new WorkflowPackageStatusCouchbase();
		workflowPackageStatusCouchbase.setPackageId(workflowPickupRequestInfo.getPackageId());
		workflowPackageStatusCouchbase.setDriverId(workflowPickupRequestInfo.getDriverId());
		workflowPackageStatusCouchbase.setRequestId(workflowPickupRequestInfo.getRequestId());
		workflowPackageStatusCouchbase.setStatus("IN_PICKUP_VERIFICATION");
		// Sending Kafka 
		workFlowProducer.sendMessage("changeRequestStatusCouchbaseRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageStatusCouchbase));
		
		runTimeService.startProcessInstanceByMessage("startDriverArrivalModule",workflowData);
	}//end receiveMessage Method
	
}
