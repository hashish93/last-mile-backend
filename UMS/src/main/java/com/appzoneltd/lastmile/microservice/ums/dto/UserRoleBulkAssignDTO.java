package com.appzoneltd.lastmile.microservice.ums.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data	
public class UserRoleBulkAssignDTO {

	@JsonProperty("users")
	private List<UserDto> users;
	
	@JsonProperty("roles")
	private List<RoleDto> roles;
	
}
