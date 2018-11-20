package com.appzoneltd.lastmile.microservices.syncengine.engine.dto;

import lombok.Data;

@Data
public class PackageCountTypeDto {
	private Long count5;
	private Long count10;
	private Long count25;
	public PackageCountTypeDto(Long count5, Long count10, Long count25) {
		this.count5 = count5;
		this.count10 = count10;
		this.count25 = count25;
	}
	public PackageCountTypeDto() {
	}
}
