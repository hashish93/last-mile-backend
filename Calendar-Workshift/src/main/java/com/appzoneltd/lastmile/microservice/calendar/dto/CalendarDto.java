package com.appzoneltd.lastmile.microservice.calendar.dto;

import lombok.Data;

@Data
public class CalendarDto {

	private Long id;
	private String dayName;
	private String status;
	private Long version;
	private Long hubId;
	

}
