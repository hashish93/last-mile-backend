package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowPackageStatus extends WorkflowBase{

	private ChangePackageStatusEnum status;

	private Long requestId;
	private Long requesterId;
	private String timeFrom;
	private String timeTo;
	private Date pickupDate;
	private Boolean isWebUser;
	private String cancelReason;
	
}
