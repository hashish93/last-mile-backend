package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import lombok.Data;

import java.util.List;

@Data
public class MyPrincipal {

	private Long userId;
	private String name;
	private String userType;
	private List<Long> hubs;
	
}