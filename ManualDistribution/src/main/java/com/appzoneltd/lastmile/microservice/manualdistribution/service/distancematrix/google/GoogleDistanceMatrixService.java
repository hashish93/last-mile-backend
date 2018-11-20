package com.appzoneltd.lastmile.microservice.manualdistribution.service.distancematrix.google;

import java.time.LocalDateTime;
import java.util.List;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.LocationDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.Journey;

public interface GoogleDistanceMatrixService {
	List<Journey> getJourniesFromSourceToDestinations(LocalDateTime startTime,LocationDto source,List<LocationDto> destinations) throws Exception;
}
