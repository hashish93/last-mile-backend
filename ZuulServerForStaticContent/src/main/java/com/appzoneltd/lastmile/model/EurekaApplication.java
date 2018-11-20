package com.appzoneltd.lastmile.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EurekaApplication {
	private String name;
	private List<EurekaInstance> instance;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EurekaInstance> getInstance() {
		return instance;
	}
	public void setInstance(List<EurekaInstance> instance) {
		this.instance = instance;
	}

}
