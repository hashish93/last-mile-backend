package com.appzoneltd.lastmile.microservice.vehicle.dao;

public class VehicleInfo extends Vehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3100999400858048994L;
	private String vehicleType;
	private String buildingName;

	public VehicleInfo() {
	}

	public VehicleInfo(String vehicleType, String buildingName) {
		super();
		this.vehicleType = vehicleType;
		this.buildingName = buildingName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}
