package com.appzoneltd.lastmile.microservice.employee.dto;

import lombok.Data;

@Data
public class EmailNotifierModel {

	private String from;
	private String to;
	private String subject;
	private String body;

}
