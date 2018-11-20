package com.appzoneltd.lastmile.microservice.ums.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoleUser {

	@JsonProperty("acceptedPrivileges")
	private Set<PrivilegeModel> privilegeModels;
	
}
