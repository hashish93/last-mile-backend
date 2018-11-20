package com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models;

import lombok.Data;

@Data
public class WorkflowDriverResponse extends WorkflowBase{

	private Long driverId;
	private boolean response;
	
}
