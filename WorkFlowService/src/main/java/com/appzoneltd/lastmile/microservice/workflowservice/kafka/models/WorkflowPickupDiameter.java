package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class WorkflowPickupDiameter extends WorkflowBase{

	private  BigDecimal diameter;
	
}
