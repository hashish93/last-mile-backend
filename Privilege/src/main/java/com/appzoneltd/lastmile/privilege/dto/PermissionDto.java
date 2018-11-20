package com.appzoneltd.lastmile.privilege.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionDto {

	private Integer id;
	private String name;
	private String displayName;
	private boolean value;
	private List<PermissionDto> children;
	
	@JsonProperty("Id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("displayName")
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@JsonProperty("value")
	public boolean isValue() {
		return value;
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}
	
	@JsonProperty("children")
	public List<PermissionDto> getChildren() {
		return children;
	}
	
	public void setChildren(List<PermissionDto> children) {
		this.children = children;
	}
	
	
	
}
