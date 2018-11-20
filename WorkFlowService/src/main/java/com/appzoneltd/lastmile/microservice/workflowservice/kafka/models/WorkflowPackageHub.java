package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import lombok.Data;

@Data
public class WorkflowPackageHub extends WorkflowBase{

	private Long hubId;
	private Long requesterId;
	
}
