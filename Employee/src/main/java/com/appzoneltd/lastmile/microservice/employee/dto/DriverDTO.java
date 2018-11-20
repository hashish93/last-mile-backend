package com.appzoneltd.lastmile.microservice.employee.dto;

import lombok.Data;

@Data
public class DriverDTO {

	private Long driverId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String nationalId;
	private Long imageId;
	
}
