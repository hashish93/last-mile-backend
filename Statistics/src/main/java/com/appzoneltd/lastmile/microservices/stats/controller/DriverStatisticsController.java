package com.appzoneltd.lastmile.microservices.stats.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservices.stats.service.DriverStatistics;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@RestController
public class DriverStatisticsController {

	@Autowired
	private DriverStatistics driverStatistics;


	
	@RequestMapping(value = "/driversRating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDriversRating(@Validated @RequestBody ChartDurationRequest duration) {

		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<StatsResponse> statsResponses = driverStatistics.driversRating(principal, duration);

		return new ResponseEntity<>(statsResponses, HttpStatus.OK);

	}
	
	

}
