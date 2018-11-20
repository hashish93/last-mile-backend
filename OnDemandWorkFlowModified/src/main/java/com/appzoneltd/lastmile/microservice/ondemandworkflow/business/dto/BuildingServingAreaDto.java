package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dto;

import java.util.List;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model.ServingArealocation;

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
