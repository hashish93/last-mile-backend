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

import com.appzoneltd.lastmile.microservices.stats.service.CustomerAgeStatistics;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@RestController
public class CustomerAgeStatisticsController {

	@Autowired
	private CustomerAgeStatistics customerAgeStatistics;

	@RequestMapping(value = "/customerAgeStatistics", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomerAgeStatistics() {

		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		
		List<StatsResponse> result = customerAgeStatistics.getAllCustomerAges(principal);

		if (result != null)
			return new ResponseEntity<>(result, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
