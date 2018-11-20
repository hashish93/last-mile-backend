package com.appzoneltd.lastmile.privilege.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BulkUserRolesDTO {

	private List<SimpleUserDTO> users;
	private List<SimpleRoleDTO> roles; 
	
	@JsonProperty("users")
	public List<SimpleUserDTO> getUsers() {
		return users;
	}
	
	public void setUsers(List<SimpleUserDTO> users) {
		this.users = users;
	}
	
	@JsonProperty("roles")
	public List<SimpleRoleDTO> getRoles() {
		return roles;
	}
	
	public void setRoles(List<SimpleRoleDTO> roles) {
		this.roles = roles;
	}
	
	
}
