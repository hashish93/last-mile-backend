package com.appzoneltd.lastmile.microservices.stats.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservices.stats.service.PickupRequestStatistics;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@RestController
public class PickupRequestStatisticsController {

	@Autowired
	private PickupRequestStatistics pickupRequestStatistics;

	@RequestMapping(value = "/countPickupRequests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCountPickupRequests(@RequestBody ChartDurationRequest chartDurationRequest) {

		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<StatsResponse> result = pickupRequestStatistics.getAllPickupRequestStatistics(principal,
				chartDurationRequest);

		if (result != null)
			return new ResponseEntity<>(result, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
