package com.appzoneltd.lastmile.privilege.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModuleDto {
	
	private Integer id;
	private String name;
	private List<PermissionDto> permissions;
	
	@JsonProperty("moduleId")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty("moduleName")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("permissions")
	public List<PermissionDto> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(List<PermissionDto> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "ModuleDto [id=" + id + ", name=" + name + ", permissions=" + permissions + "]";
	}
	
	
	
}
