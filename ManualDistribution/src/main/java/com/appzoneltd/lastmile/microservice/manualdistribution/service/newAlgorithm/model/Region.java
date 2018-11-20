package com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.model;

import java.util.List;

import lombok.Data;

@Data
public class Region {

	private String name;
	private List<OrderPoint> orderPoints;

}
