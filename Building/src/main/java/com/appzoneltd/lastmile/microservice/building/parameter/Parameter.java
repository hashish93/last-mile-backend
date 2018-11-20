package com.appzoneltd.lastmile.microservice.building.parameter;

import java.io.Serializable;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;

import lombok.Data;

@Data
public class Parameter implements Serializable {
	private static final long serialVersionUID = 6808330546279356454L;
	private Long id;
	private String status;
	private int page;
	private int maxResult;
	private OrderBy orderBy;
}
