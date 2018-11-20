package com.appzoneltd.lastmile.microservice.lookup.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Parameter implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long hubId;
	private Long roleId;
	private Long userId;
	private String name;
	private boolean active;
	private List<Long> hubs;
}
