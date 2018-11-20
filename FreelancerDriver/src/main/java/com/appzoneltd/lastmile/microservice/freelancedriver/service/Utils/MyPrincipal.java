package com.appzoneltd.lastmile.microservice.freelancedriver.service.Utils;

import lombok.Data;

import java.util.List;

@Data
public class MyPrincipal {

	private Long userId;
	private String name;
	private String userType;
	private List<Long> hubs;
	
}
