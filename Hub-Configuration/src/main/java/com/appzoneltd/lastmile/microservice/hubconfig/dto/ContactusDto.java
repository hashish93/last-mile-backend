package com.appzoneltd.lastmile.microservice.hubconfig.dto;

import java.util.List;

import lombok.Data;

@Data
public class ContactusDto {

	private String lang;
	private String hotlineTitle;
	private String hotlineNumber;
	List<ContactUsEmailDto> emails;
	private String dailyWorkingHoursFrom;
	private String dailyWorkingHoursTo;
	private String vacationWorkingHoursFrom;
	private String vacationWorkingHoursTo;

}
