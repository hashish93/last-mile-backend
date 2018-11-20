package com.appzoneltd.lastmile.microservice.details.kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.JobOrder;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationModel;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.details.kafka.model.ActiveVehicleModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;


	@Component
	public class DriverPositionConsumer {
		
		@Autowired
		private ObjectMapper mapper ;

		@Autowired
		private GeoApiContext geoApiContext;
		
		@Autowired
		private RegistrationRepository registrationRepository;
		
		@KafkaListener(topics = "DRIVER_SOURCE_POSITION_DETECTION")
		public void getCurrentDriverPosition(@Payload String payload)
				throws JsonParseException, JsonMappingException, IOException, InterruptedException {
			
			ActiveVehicleModel activeVehicleModel=mapper.readValue(payload, ActiveVehicleModel.class);
			updateSourceLocation(activeVehicleModel);
			
			
		}
		
		@Transactional
		private void updateSourceLocation(ActiveVehicleModel activeVehicleModel) {
			RegistrationModel registrationModel = registrationRepository.findOne(activeVehicleModel.getActiveVehicleId());
			List<JobOrder> jobOrders = new ArrayList<>();
			for (JobOrder jobOrder : registrationModel.getJobOrders()) {
				if (activeVehicleModel.getRequestId().intValue() == jobOrder.getJobOrderId().intValue()) {
					jobOrder.setCurrentOrder(true);
					String currentSourceLocation=getFormattedAddress(registrationModel.getLocation().getLatitude(), registrationModel.getLocation().getLongitude());
					registrationModel.setActiveVehicleAddress(currentSourceLocation);
					jobOrder.setActiveVehicleAddress(currentSourceLocation);					
				} else{
					jobOrder.setCurrentOrder(false);
				}
				jobOrders.add(jobOrder);
			}
			registrationModel.setJobOrders(jobOrders);			
			registrationRepository.save(registrationModel);
		}

		private String getFormattedAddress(String lat, String lng) {
			final GeocodingApiRequest geocodingApiRequest = GeocodingApi.newRequest(geoApiContext);
			GeocodingResult[] geocodingResults = null;
			try {
				geocodingResults = geocodingApiRequest.latlng(new LatLng(Double.parseDouble(lat)
						, Double.parseDouble(lng))).await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (geocodingResults != null && geocodingResults.length != 0)
				return geocodingResults[0].formattedAddress;
			return null;
		}

		
	}

