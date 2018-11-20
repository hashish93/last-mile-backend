package com.appzoneltd.lastmile.microservice.building.ServingArea.model;

public class BuildingPoint {

	private double latitude;
	private double longitude;

	public BuildingPoint() {
	}

	public BuildingPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "GeoPoint [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
