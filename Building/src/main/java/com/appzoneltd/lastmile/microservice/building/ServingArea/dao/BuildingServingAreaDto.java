package com.appzoneltd.lastmile.microservice.building.ServingArea.dao;

import java.util.List;

public class BuildingServingAreaDto {

	private Long buildingId;

	private List<ServingArealocation> locations;

	public BuildingServingAreaDto(Long buildingId, List<ServingArealocation> locations) {
		this.buildingId = buildingId;
		this.locations = locations;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public List<ServingArealocation> getLocations() {
		return locations;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public void setLocations(List<ServingArealocation> locations) {
		this.locations = locations;
	}

}
