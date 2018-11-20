package com.appzoneltd.lastmile.microservice.workflowservice.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageStatusCouchbase;
import com.appzoneltd.lastmile.microservice.workflowservice.service.JobOrdersService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChangeRequestStatusCouchbaseConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private JobOrdersService jobOrdersService;

	@KafkaListener(topics = "changeRequestStatusCouchbaseRequest")
	public void assignTheOrderToDriver(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		// Getting Driver Action

		WorkflowPackageStatusCouchbase workflowPackageStatusCouchbase = mapper.readValue(payload,
				WorkflowPackageStatusCouchbase.class);
		System.out.println(
				">>>>>>>>>>>>>>>>>>>>>>>****************************************** workflowPackageStatusCouchbase "
						+ workflowPackageStatusCouchbase.toString());
		if (workflowPackageStatusCouchbase.getStatus().equalsIgnoreCase(ChangePackageStatusEnum.CANCELED.name()))
			jobOrdersService.deleteCancelledRequest(workflowPackageStatusCouchbase.getRequestId());
		else
			// Changing the Status of the Job Order
			jobOrdersService.changeRequestStatus(workflowPackageStatusCouchbase.getDriverId(),
					workflowPackageStatusCouchbase.getRequestId(), workflowPackageStatusCouchbase.getStatus());

		System.out.println("DATA CHANGED IN COUCHBASE TO  " + workflowPackageStatusCouchbase.toString());

	}// end nearByVehicleRequest Method

}