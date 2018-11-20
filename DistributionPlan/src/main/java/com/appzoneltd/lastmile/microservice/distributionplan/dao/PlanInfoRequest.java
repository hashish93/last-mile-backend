package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.util.List;

public class PlanInfoRequest {

	private List<Long> activeVehicleIds;

	public List<Long> getActiveVehicleIds() {
		return activeVehicleIds;
	}

	public void setActiveVehicleIds(List<Long> activeVehicleIds) {
		this.activeVehicleIds = activeVehicleIds;
	}

	@Override
	public String toString() {
		return "PlanInfoRequest [activeVehicleIds=" + activeVehicleIds + "]";
	}

}
