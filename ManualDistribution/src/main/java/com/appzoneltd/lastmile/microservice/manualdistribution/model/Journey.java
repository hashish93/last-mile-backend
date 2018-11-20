package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.LocationDto;

public class Journey {
	
	private LocationDto source;
	private LocationDto destination;
	private Long timeTakenRoutingEngineInSeconds;
	private String timeTakenRoutingEngineInText ;

	public LocationDto getSource() {
		return source;
	}

	public LocationDto getDestination() {
		return destination;
	}

	public void setSource(LocationDto source) {
		this.source = source;
	}

	public void setDestination(LocationDto destination) {
		this.destination = destination;
	}

	public Long getTimeTakenRoutingEngineInSeconds() {
		return timeTakenRoutingEngineInSeconds;
	}

	public void setTimeTakenRoutingEngineInSeconds(Long timeTakenRoutingEngineInSeconds) {
		this.timeTakenRoutingEngineInSeconds = timeTakenRoutingEngineInSeconds;
	}

	public String getTimeTakenRoutingEngineInText() {
		return timeTakenRoutingEngineInText;
	}

	public void setTimeTakenRoutingEngineInText(String timeTakenRoutingEngineInText) {
		this.timeTakenRoutingEngineInText = timeTakenRoutingEngineInText;
	}

	


}
