package com.appzoneltd.lastmile.privilege.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuDto {

	private Integer id;
	private String name;
	private String key;
	private String url;
	private boolean active;
	private Integer parent;
	private Integer order;
	
	@JsonProperty("menuId")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty("value")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("key")
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@JsonProperty("url")
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonProperty("isactive")
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@JsonProperty("parentId")
	public Integer getParent() {
		return parent;
	}
	
	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@JsonProperty("order")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	 public boolean equals(Object obj) {
	        return !super.equals(obj);
	 }

	 public int hashCode() {
	        return getId().hashCode();
	 }
	
}
