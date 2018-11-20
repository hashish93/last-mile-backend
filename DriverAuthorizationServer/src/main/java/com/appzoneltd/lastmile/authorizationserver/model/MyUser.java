package com.appzoneltd.lastmile.authorizationserver.model;

import lombok.Data;

@Data
public class MyUser {

	private long userId;
	private String username;
	private String email;
	private String phone;
	private String status;
	private String description;
	private Long userTypeId;
	private String userType;
	private int pageSize;
	private Long personalPhotoId;

}
