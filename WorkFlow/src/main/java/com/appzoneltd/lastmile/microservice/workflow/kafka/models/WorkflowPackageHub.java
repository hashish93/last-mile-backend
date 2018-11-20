package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import lombok.Data;

@Data
public class WorkflowPackageHub extends WorkflowBase{

	private Long hubId;
	private Long requesterId;
	
}
