package com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models;

import lombok.Data;

@Data
public class WorkflowPackageLocation extends WorkflowBase{

	private String latitude;
	private String longitude;
	
}
