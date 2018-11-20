package com.appzoneltd.lastmile.privilege.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDTO {
	
	private Integer id;
	private String name;
	private String description;
	private List<ModuleDto> modules;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty("privileges")
	public List<ModuleDto> getModules() {
		return modules;
	}
	
	public void setModules(List<ModuleDto> modules) {
		this.modules = modules;
	}

	
	
}
