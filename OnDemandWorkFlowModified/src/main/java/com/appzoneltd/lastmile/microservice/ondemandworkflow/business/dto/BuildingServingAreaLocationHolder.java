package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dto;

public class BuildingServingAreaLocationHolder {

	private Long buildingId;

	private String latitude;

	private String longitude;
	
	
	public BuildingServingAreaLocationHolder(){
		
	}

	public BuildingServingAreaLocationHolder(String latitude, String longitude) {

		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
