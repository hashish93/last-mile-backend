package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;

import lombok.Data;

@Data
public class WorkflowPickupRequestInfo{

	private Long packageId;
	private Long requestId;
	private Long driverId;
	private Long requesterId;
	private String address;
	
}
