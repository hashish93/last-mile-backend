package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowPackageStatus extends WorkflowBase{

	private ChangePackageStatusEnum status;
	private boolean admin;
	private Long userId;

	private Long requestId;
	private Long requesterId;
	private String timeFrom;
	private String timeTo;
	private Date pickupDate;
	private Boolean isWebUser;
	private Boolean isDriver;
	private Long driverId;
	private String cancelReason;
}
