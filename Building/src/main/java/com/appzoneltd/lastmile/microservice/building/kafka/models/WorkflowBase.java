package com.appzoneltd.lastmile.microservice.building.kafka.models;

import lombok.Data;

@Data
public class WorkflowBase {

	private Long packageId;
	private String requestType;
}
