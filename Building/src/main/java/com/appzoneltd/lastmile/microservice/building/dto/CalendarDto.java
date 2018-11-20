package com.appzoneltd.lastmile.microservice.building.dto;

import lombok.Data;

@Data
public class CalendarDto {
	private Long id;
	private String dayName;
	private String status;
	private Long hubId;
}
