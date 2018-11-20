package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import lombok.Data;

@Data
public class WorkFlowResult {

	private Long packageId;
	private WorkFlowResultEnum status;
	private String message;	
	
	
}
