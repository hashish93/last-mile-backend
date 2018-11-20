package com.appzoneltd.lastmile.privilege.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModulePrivilegeDTO {

	private List<ModuleDto> modules;

	@JsonProperty("privileges")
	public List<ModuleDto> getModules() {
		return modules;
	}

	public void setModules(List<ModuleDto> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "ModulePrivilegeTransformer [modules=" + modules + "]";
	}
	
	
	
}
