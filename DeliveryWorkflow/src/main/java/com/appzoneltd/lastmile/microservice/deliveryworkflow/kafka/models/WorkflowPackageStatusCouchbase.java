package com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.models;

import lombok.Data;

@Data
public class WorkflowPackageStatusCouchbase extends WorkflowBase{

	private Long driverId;
	private Long requestId;
	private String status;
	
}
