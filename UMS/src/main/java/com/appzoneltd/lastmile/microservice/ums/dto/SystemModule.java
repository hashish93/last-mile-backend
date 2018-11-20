package com.appzoneltd.lastmile.microservice.ums.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SystemModule {

	@JsonProperty("moduleId")
	private Long id;
	
	@JsonProperty("moduleName")
	private String name;
	
	@JsonProperty("permissions")
	private List<ModulePermission> modulePermissions;
	
}
