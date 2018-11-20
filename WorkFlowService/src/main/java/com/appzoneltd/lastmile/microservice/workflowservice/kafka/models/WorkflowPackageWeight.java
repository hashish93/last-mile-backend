package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class WorkflowPackageWeight extends WorkflowBase{

	private BigDecimal weight;
	
}
