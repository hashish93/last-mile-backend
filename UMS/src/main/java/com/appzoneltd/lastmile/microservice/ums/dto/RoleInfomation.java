package com.appzoneltd.lastmile.microservice.ums.dto;

import lombok.Data;

@Data
public class RoleInfomation {

	private Long id;
	private String name;
	private String description;
	private String hubName;
	private boolean enabled;
	private boolean editable;

}
