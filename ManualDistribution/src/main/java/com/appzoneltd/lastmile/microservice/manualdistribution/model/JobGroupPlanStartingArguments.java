package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.time.LocalDateTime;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.LocationDto;

public class JobGroupPlanStartingArguments {
	private LocationDto startingLocation;
	private LocalDateTime startingTime;
	
	public LocationDto getStartingLocation() {
		return startingLocation;
	}
	public void setStartingLocation(LocationDto startingLocation) {
		this.startingLocation = startingLocation;
	}
	public LocalDateTime getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}
	
	public void clear(){
		startingLocation=null;
		startingTime=null;
	}
}
