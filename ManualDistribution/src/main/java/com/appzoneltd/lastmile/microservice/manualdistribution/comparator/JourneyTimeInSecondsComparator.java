package com.appzoneltd.lastmile.microservice.manualdistribution.comparator;

import java.util.Comparator;

import com.appzoneltd.lastmile.microservice.manualdistribution.model.Journey;

public class JourneyTimeInSecondsComparator implements Comparator<Journey> {

	@Override
	public int compare(Journey o1, Journey o2) {
		return o1.getTimeTakenRoutingEngineInSeconds().compareTo(o2.getTimeTakenRoutingEngineInSeconds());
	}

}
