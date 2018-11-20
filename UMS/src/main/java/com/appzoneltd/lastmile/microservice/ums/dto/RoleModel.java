package com.appzoneltd.lastmile.microservice.ums.dto;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoleModel {
	
	@JsonProperty("id")
	private Long id;
	
	@NotEmpty(message = "Role Name required")
    @Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -_.,]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]*", message = "Role Name Error")
	@Size(max = 32 , message="Role name max length 32 letters")
	@JsonProperty("name")
	private String name;
	
	@NotEmpty(message = "Role Description required")
	@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -_.,]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]*", message = "Role Description Error")
	@Size(max = 250 , message="Role name length 250 letters")
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("enabled")
	private boolean enabled;
	
	@JsonProperty("acceptedPrivileges")
	private List<PrivilegeModel> privilegeModels;
	
	@JsonProperty("users")
	private List<UserModel> userModels;
	
	@JsonProperty("hubId")
	private Long hubId;
	
}
