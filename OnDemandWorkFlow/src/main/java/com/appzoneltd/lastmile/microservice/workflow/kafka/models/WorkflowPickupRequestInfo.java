package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import lombok.Data;

@Data
public class WorkflowPickupRequestInfo extends WorkflowBase{

	private Long requestId;
	private Long requesterId;
	private Long driverId;
	private String address;
	
}
