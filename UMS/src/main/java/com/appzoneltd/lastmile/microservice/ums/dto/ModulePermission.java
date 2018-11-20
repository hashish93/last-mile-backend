package com.appzoneltd.lastmile.microservice.ums.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModulePermission {
	
	@JsonProperty("Id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("displayName")
	private String displayName;
	
	@JsonProperty("value")
	private boolean value;
	
	@JsonProperty("children")
	private List<ModulePermission> children;

}
