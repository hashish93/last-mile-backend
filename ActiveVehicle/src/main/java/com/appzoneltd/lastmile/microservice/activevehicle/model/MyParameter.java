package com.appzoneltd.lastmile.microservice.activevehicle.model;

import java.io.Serializable;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;

import lombok.Data;

@Data
public class MyParameter implements Serializable {
	private static final long serialVersionUID = 6808330546279356454L;
	private Long id;
	private int page;
	private int maxResult;
	private OrderBy orderBy;
	private Long hubId;
}
