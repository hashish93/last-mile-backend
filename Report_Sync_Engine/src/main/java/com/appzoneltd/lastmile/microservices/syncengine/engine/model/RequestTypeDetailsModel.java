package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import java.util.Date;

import lombok.Data;

@Data
public class RequestTypeDetailsModel {

	private Long requestId;
	private Long hubId;
	private String requestType;
	private Date created;
	private Date updated;
}
