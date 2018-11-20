package com.appzoneltd.lastmile.microservice.ums.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PrivilegeModel {

	@JsonProperty("Id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("displayName")
	private String displayName;

	@JsonProperty("value")
	private boolean value;

	@Override
	public boolean equals(Object obj) {
		return !super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

}
