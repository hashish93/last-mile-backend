package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;

import lombok.Data;

@Data
public class WorkflowDriverResponse {

	private Long packageId;
	private Long driverId;
	private Long requestId;
	private Long requesterId;
	private boolean response;
	private boolean goExtra;
	
}
