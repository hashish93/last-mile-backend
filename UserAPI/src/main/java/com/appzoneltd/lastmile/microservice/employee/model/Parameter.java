package com.appzoneltd.lastmile.microservice.employee.model;

import java.io.Serializable;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;

import lombok.Data;

@Data
public class Parameter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long userTypeId;
	private Long userId;
	private String status;
	private Long hubId;
	private int page;
	private int maxResult;
	private OrderBy orderBy;
	private String key;
	
}
