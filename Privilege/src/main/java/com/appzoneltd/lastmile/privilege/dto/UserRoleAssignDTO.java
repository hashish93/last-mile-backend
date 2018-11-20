package com.appzoneltd.lastmile.privilege.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRoleAssignDTO {

	private Integer userId;
	private List<SimpleRoleDTO> simpleRoleDTOs; 
	
	@JsonProperty("userId")
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@JsonProperty("roles")
	public List<SimpleRoleDTO> getSimpleRoleDTOs() {
		return simpleRoleDTOs;
	}

	public void setSimpleRoleDTOs(List<SimpleRoleDTO> simpleRoleDTOs) {
		this.simpleRoleDTOs = simpleRoleDTOs;
	}

	
	
}
