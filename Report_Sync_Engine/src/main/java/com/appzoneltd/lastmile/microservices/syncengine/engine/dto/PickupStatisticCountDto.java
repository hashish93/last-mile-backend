package com.appzoneltd.lastmile.microservices.syncengine.engine.dto;

import lombok.Data;

@Data
public class PickupStatisticCountDto {

	private Long pickedupCount;
	private Long CanceledCount;
	private Long noCapacityCount;
	private Long noCoverageCount;

	
	
	public PickupStatisticCountDto(Long pickedupCount, Long canceledCount, Long noCapacityCount, Long noCoverageCount) {
		this.pickedupCount = pickedupCount;
		CanceledCount = canceledCount;
		this.noCapacityCount = noCapacityCount;
		this.noCoverageCount = noCoverageCount;
	}

	public PickupStatisticCountDto() {
		
	}

}
