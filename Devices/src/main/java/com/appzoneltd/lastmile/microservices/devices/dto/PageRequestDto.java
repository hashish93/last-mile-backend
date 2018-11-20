package com.appzoneltd.lastmile.microservices.devices.dto;

import lombok.Data;

@Data
public class PageRequestDto {

	private int pageOffset;
	
	private int pageSize;

	private String orderBy;
	
	private Long hubId;
}
