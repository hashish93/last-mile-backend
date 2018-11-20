package com.appzoneltd.lastmile.microservice.returnrequest.modal;

import java.util.List;

import lombok.Data;

@Data
public class MyPrincipal {

	private Long userId;
	private String name;
	private String userType;
	private List<Long> hubs;
	
}
