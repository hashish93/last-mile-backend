package com.appzoneltd.lastmile.microservices.syncengine.engine.dto;

import lombok.Data;

@Data
public class RequestCountTypeDto {
	private Long onDemandCount;
	private Long scheduledCount;
	private Long returnCount;
	private Long deliveryCount;
	
	public RequestCountTypeDto() {
	}

	public RequestCountTypeDto(Long onDemandCount, Long scheduledCount, Long returnCount, Long deliveryCount) {
		super();
		this.onDemandCount = onDemandCount;
		this.scheduledCount = scheduledCount;
		this.returnCount = returnCount;
		this.deliveryCount = deliveryCount;
	}
}
