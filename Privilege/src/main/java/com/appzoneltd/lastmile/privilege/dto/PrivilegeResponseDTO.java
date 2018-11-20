package com.appzoneltd.lastmile.privilege.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeResponseDTO {

	private Integer id;
	private String name;
	private String displayName;
	private boolean value;
	
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

	@Override
	public String toString() {
		return "PrivilegeResponseDTO [id=" + id + ", name=" + name + ", displayName=" + displayName + ", value=" + value
				+ "]";
	}
	
	 @Override
	    public boolean equals(Object obj) {
	        return !super.equals(obj);
	    }

	    public int hashCode() {
	        return getId().hashCode();
	    }
	
}
