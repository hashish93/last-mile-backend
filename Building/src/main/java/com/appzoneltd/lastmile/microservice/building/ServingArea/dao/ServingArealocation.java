package com.appzoneltd.lastmile.microservice.building.ServingArea.dao;

public class ServingArealocation {

	private String latitude;

	private String longitude;

	private String type;
	
	public ServingArealocation(){
		
	}
	
	public ServingArealocation(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
