package com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models;

import lombok.Data;

@Data
public class ResceduledWorkFlowBase extends WorkflowBase {
	private String RequestStatus;
	private String senderType;
}
