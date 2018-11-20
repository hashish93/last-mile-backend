package com.appzoneltd.lastmile.privilege.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.appzoneltd.lastmile.privilege.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleResponseDTO {

	private Integer id;
	private String name;
	private String description;
	private boolean enabled;
	private List<PrivilegeResponseDTO> privilegeResponseDTOs;
	private Set<User> users;

	
	@JsonProperty("Id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Role Name required")
    @Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -_.,]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]*", message = "Role Name Error")
	@Size(max = 32 , message="Role name max length 32 letters")
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotEmpty(message = "Role Description required")
	@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -_.,]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]*", message = "Role Description Error")
	@Size(max = 250 , message="Role name length 250 letters")
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty("enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("acceptedPrivileges")
	public List<PrivilegeResponseDTO> getPrivilegeResponseDTOs() {
		return privilegeResponseDTOs;
	}
	
	public void setPrivilegeResponseDTOs(List<PrivilegeResponseDTO> privilegeResponseDTOs) {
		this.privilegeResponseDTOs = privilegeResponseDTOs;
	}

	@JsonProperty("users")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}



	@Override
	public String toString() {
		return "RoleResponseDTO [id=" + id + ", name=" + name + ", description=" + description
				+ ", privilegeResponseDTOs=" + privilegeResponseDTOs + "]";
	}
	
}
