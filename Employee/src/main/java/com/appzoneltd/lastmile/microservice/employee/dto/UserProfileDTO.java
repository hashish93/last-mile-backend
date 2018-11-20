package com.appzoneltd.lastmile.microservice.employee.dto;

import lombok.Data;

@Data
public class UserProfileDTO {

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private Long countryCodeId;
	private String phoneNumber;
	private String nationalId;
	private Long imageId;
	private String oldPassword;
	private String newPassword;
	private String repeatedPassword;
	private String userName;

}
