package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;

import lombok.Data;

@Data
public class ResceduledWorkFlowBase extends WorkflowBase {
	private String RequestStatus;
	private String senderType;
}
