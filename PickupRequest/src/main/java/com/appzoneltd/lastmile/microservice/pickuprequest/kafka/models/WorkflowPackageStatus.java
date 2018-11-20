package com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowPackageStatus extends WorkflowBase {

	private ChangePackageStatusEnum status;
	private boolean admin;
	private Long userId;

	// Rescheduling On-demand member variables

	private Long requestId;
	private Long requesterId;
	private String timeFrom;
	private String timeTo;
	private Date pickupDate;
	private Boolean isWebUser;
	private String cancelReason;
}
