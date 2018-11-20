package com.appzoneltd.lastmile.microservice.ums.dto;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	private String email;
	private String phone;
	private String userType;
	
}
