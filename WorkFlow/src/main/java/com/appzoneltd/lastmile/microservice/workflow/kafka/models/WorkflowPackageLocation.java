package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import lombok.Data;

@Data
public class WorkflowPackageLocation extends WorkflowBase{

	private String latitude;
	private String longitude;
	
}
