package com.appzoneltd.lastmile.microservice.building.kafka.models;

import lombok.Data;

@Data
public class WorkFlowResult {

	private Long packageId;
	private WorkFlowResultEnum status;
	private String message;	
	
	
}
