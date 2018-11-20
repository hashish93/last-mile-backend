package com.appzoneltd.lastmile.microservice.calendar.model;

import java.util.List;

import lombok.Data;

@Data
public class MyPrincipal {

	private String name;
	private String userType;
	private List<Long> hubs;
	
}
