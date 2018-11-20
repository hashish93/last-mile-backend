package com.appzoneltd.lastmile.microservices.syncengine.engine.dto;

import lombok.Data;

@Data
public class GoExtraCountDto {
	private Long rejectedCount;
	private Long acknowledgeCount;
	private Long canceledCount;
	
	public GoExtraCountDto() {
	}

	public GoExtraCountDto(Long rejectedCount, Long acknowledgeCount, Long canceledCount) {
		super();
		this.rejectedCount = rejectedCount;
		this.acknowledgeCount = acknowledgeCount;
		this.canceledCount = canceledCount;
	}
}
