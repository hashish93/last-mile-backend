package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import lombok.Data;

@Data
public class WorkflowDriverResponse extends WorkflowBase{

	private Long driverId;
	private Long requestId;
	private Long requesterId;
	private boolean response;
	private boolean goExtra;
	
	
}
