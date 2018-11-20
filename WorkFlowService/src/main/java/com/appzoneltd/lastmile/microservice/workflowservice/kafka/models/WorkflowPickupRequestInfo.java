package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import lombok.Data;

@Data
public class WorkflowPickupRequestInfo extends WorkflowBase{

	private Long requestId;
	private Long requesterId;
	private String address;
	
}
