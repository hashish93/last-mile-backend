package com.appzoneltd.lastmile.microservices.syncengine.engine.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GoExtraDetailsDto{
	private Long id;
	private Long requestId;
	private Long hubId;
	private String requestStatus;
	private Date created;
	private Date updated;
	public GoExtraDetailsDto(Long id ,Long requestId, Long hubId, String requestStatus, Date created) {
		this.id = id;
		this.requestId = requestId;
		this.hubId = hubId;
		this.requestStatus = requestStatus;
		this.created = created;
	}
	public GoExtraDetailsDto() {
		
	}
	
	
	
}
