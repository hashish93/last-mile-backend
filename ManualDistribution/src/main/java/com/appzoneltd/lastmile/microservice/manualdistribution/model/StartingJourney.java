package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.time.LocalDateTime;

public class StartingJourney {
	private Journey journey;
	private LocalDateTime startingTime;
	
	public Journey getJourney() {
		return journey;
	}
	public LocalDateTime getStartingTime() {
		return startingTime;
	}
	public void setJourney(Journey journey) {
		this.journey = journey;
	}
	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}
	
	

}
