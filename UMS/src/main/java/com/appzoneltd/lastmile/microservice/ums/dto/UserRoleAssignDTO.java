package com.appzoneltd.lastmile.microservice.ums.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserRoleAssignDTO {

	@JsonProperty("userId")
	private Long userId;
	
	@JsonProperty("roles")
	private List<RoleDto> roleDtos; 
		
}
