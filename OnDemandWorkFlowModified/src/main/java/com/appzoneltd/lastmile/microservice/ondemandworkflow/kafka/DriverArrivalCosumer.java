package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowPickupRequestInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DriverArrivalCosumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "DRIVER_ARRIVAL_ACKNOLEDGMENT")
	public void receiveMessage(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PAYLOAD " + payload);
		WorkflowPickupRequestInfo workflowPickupRequestInfo = mapper.readValue(payload,WorkflowPickupRequestInfo.class);

		/// generate Map to Complete Flow
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", workflowPickupRequestInfo.getPackageId());
		workflowData.put("requestId", workflowPickupRequestInfo.getRequestId());

		System.out.println(">>>>>>>>>>>>>>>>>DRIVER_ARRIVAL_ACKNOLEDGMENT " + workflowPickupRequestInfo.toString());

//		WorkflowPackageStatusCouchbase workflowPackageStatusCouchbase = new WorkflowPackageStatusCouchbase();
//		workflowPackageStatusCouchbase.setPackageId(workflowPickupRequestInfo.getPackageId());
//		workflowPackageStatusCouchbase.setDriverId(workflowPickupRequestInfo.getDriverId());
//		workflowPackageStatusCouchbase.setRequestId(workflowPickupRequestInfo.getRequestId());
//		workflowPackageStatusCouchbase.setStatus("IN_PICKUP_VERIFICATION");
//		// Sending Kafka
//		workFlowProducer.sendMessage("changeRequestStatusCouchbaseRequest",
//				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageStatusCouchbase));

		runTimeService.startProcessInstanceByMessage("startDriverArrivalModule", workflowData);
	}// end receiveMessage Method

}
