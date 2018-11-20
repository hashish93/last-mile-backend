package com.appzoneltd.lastmile.microservice.manualdistribution.service.distancematrix.google;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.LocationDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.Journey;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

@Service
public class GoogleDistanceMatrixServiceImpl implements GoogleDistanceMatrixService {

	final static String apiKey = "AIzaSyCd__W4BmUNIp6lR77URTZv67dCcPT7WTo";
	private GeoApiContext geoApiContext = new GeoApiContext().setApiKey(apiKey);

	@Override
	public List<Journey> getJourniesFromSourceToDestinations(LocalDateTime startTime, LocationDto source,
			List<LocationDto> destinations) throws Exception {

		String origins = source.getLatitude() + "," + source.getLongtitude();

		String[] destinationsArrays = new String[destinations.size()];

		int index = 0;

		for (LocationDto destination : destinations) {
			destinationsArrays[index++] = destination.getLatitude() + "," + destination.getLongtitude();
		}

		DistanceMatrixApiRequest distanceMatrixApiRequest = DistanceMatrixApi.getDistanceMatrix(geoApiContext,
				new String[] { origins }, destinationsArrays);
		distanceMatrixApiRequest.trafficModel(TrafficModel.BEST_GUESS);
		distanceMatrixApiRequest.mode(TravelMode.DRIVING);
		DateTime dateTime = DateTime.now();
		dateTime.withMillis(startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		distanceMatrixApiRequest.departureTime(dateTime);

		distanceMatrixApiRequest.units(Unit.METRIC);

		DistanceMatrix distanceMatrix = distanceMatrixApiRequest.await();
		List<Journey> allJourney = new ArrayList<>();

		for (int i = 0; i < distanceMatrix.destinationAddresses.length; i++) {
			if(distanceMatrix.rows[0].elements[i].duration != null){
				Long timeInSeconds = distanceMatrix.rows[0].elements[i].duration.inSeconds;
				String timeTakenValue = distanceMatrix.rows[0].elements[i].duration.humanReadable;
				Journey joureny = new Journey();
				joureny.setSource(source);
				joureny.setDestination(destinations.get(i));
				joureny.setTimeTakenRoutingEngineInSeconds(timeInSeconds);
				joureny.setTimeTakenRoutingEngineInText(timeTakenValue);

				allJourney.add(joureny);
			}
		}

		return allJourney;
	}

}
