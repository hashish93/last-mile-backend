package com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models;

import lombok.Data;

@Data
public class WorkflowPickupRequestInfo extends WorkflowBase{

	private Long requestId;
	private Long requesterId;
	private String address;
	
}
