package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import java.util.Date;

import lombok.Data;

@Data
public class GoExtraDetailsModel {
	
	private String requestStatus;
	private Date created;
	private Date updated;

}
