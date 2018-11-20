package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import lombok.Data;

@Data
public class GoExtraSummeryReportModel {
	private Long hubId;
	private String requestStatus;
	private Long count;
	
	public GoExtraSummeryReportModel(Long hubId, String requestStatus , Long count) {
		super();
		this.hubId = hubId;
		this.requestStatus = requestStatus;
		this.count = count;
	}
}
