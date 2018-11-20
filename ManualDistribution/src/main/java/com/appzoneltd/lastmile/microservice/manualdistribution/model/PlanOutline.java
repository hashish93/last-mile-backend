package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.io.Serializable;
import java.util.List;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.Location;

public class PlanOutline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long activeVehicleId ;
	
	private List<Long> jobOrderIds;
	
	private Location startingLocation ;


	public List<Long> getJobOrderIds() {
		return jobOrderIds;
	}

	public Location getStartingLocation() {
		return startingLocation;
	}

	public void setJobOrderIds(List<Long> jobOrderIds) {
		this.jobOrderIds = jobOrderIds;
	}

	public void setStartingLocation(Location startingLocation) {
		this.startingLocation = startingLocation;
	}

	public Long getActiveVehicleId() {
		return activeVehicleId;
	}

	public void setActiveVehicleId(Long activeVehicleId) {
		this.activeVehicleId = activeVehicleId;
	}

	@Override
	public String toString() {
		return "PlanOutline{" +
				"activeVehicleId=" + activeVehicleId +
				", jobOrderIds=" + jobOrderIds +
				", startingLocation=" + startingLocation +
				'}';
	}
}
