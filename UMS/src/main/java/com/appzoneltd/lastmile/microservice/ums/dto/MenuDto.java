package com.appzoneltd.lastmile.microservice.ums.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MenuDto {

	@JsonProperty("menuId")
	private Long id;
	
	@JsonProperty("value")
	private String name;
	
	@JsonProperty("key")
	private String key;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("isactive")
	private boolean active;
	
	@JsonProperty("parentId")
	private Long parent;
	
	@JsonProperty("order")
	private Long order;
	
	@Override
	public boolean equals(Object obj) {
		return !super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	
}
