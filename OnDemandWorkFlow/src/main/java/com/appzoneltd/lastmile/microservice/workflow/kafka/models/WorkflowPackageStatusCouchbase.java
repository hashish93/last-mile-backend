package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import lombok.Data;

@Data
public class WorkflowPackageStatusCouchbase extends WorkflowBase{

	private Long driverId;
	private Long requestId;
	private Long requesterId;
	private String status;
	
}
