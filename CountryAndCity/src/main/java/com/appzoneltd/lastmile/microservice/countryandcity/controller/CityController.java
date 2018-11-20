package com.appzoneltd.lastmile.microservice.countryandcity.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.countryandcity.dto.City;
import com.appzoneltd.lastmile.microservice.countryandcity.service.CityService;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class CityController {

	private final CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@RequestMapping(value = ("/cities"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getCities() {
		return new ResponseEntity<List<City>>(cityService.getAllCities(LocaleContextHolder.getLocale()), HttpStatus.OK);
	}

	@RequestMapping(value = ("/cities"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getCities(@RequestBody Map<String, Long> params) {
		return new ResponseEntity<List<City>>(
				cityService.getCitiesByCountryId(params.get("id"), LocaleContextHolder.getLocale()), HttpStatus.OK);
	}

	@RequestMapping(value = ("/coveredCities"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getCoveredCities() {
		return new ResponseEntity<List<City>>(
				cityService.getCoveredCities(LocaleContextHolder.getLocale()), HttpStatus.OK);
	}
}
