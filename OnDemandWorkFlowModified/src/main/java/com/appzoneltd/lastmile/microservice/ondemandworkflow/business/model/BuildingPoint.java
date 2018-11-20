package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model;

import lombok.Data;

@Data
public class BuildingPoint {

	private double latitude;
	private double longitude;

	public BuildingPoint() {}

	public BuildingPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
