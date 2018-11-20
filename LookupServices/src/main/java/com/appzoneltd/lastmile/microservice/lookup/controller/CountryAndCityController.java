package com.appzoneltd.lastmile.microservice.lookup.controller;

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

import com.appzoneltd.lastmile.microservice.lookup.dto.CountryCodesDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.CountryDto;
import com.appzoneltd.lastmile.microservice.lookup.service.CountryAndCityService;

@RequestMapping("/countryandcity")
@RestController
public class CountryAndCityController {

	@Autowired
	private CountryAndCityService countryAndCityService;
	
	@RequestMapping(value = ("/countries"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCountries() {
		List<CountryDto> countryDtos=countryAndCityService.getAllCountries(LocaleContextHolder.getLocale());
		return new ResponseEntity<>(countryDtos,HttpStatus.OK);
	}

	@RequestMapping(value = ("/countryCodes"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCountryCodes() {
		List<CountryCodesDto> countryCodesDtos = countryAndCityService.getCountryCodes();
		return new ResponseEntity<>(countryCodesDtos,HttpStatus.OK);
	}
	
	@RequestMapping(value = ("/cities"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCities() {
		return new ResponseEntity<>(countryAndCityService.getAllCities(LocaleContextHolder.getLocale()), HttpStatus.OK);
	}

	@RequestMapping(value = ("/cities"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCitiesById(@RequestBody Map<String, Long> params) {
		return new ResponseEntity<>(
				countryAndCityService.getCitiesByCountryId(params.get("id"), LocaleContextHolder.getLocale()), HttpStatus.OK);
	}
	
	@RequestMapping(value = ("/coveredCities"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCoveredCities() {
		return new ResponseEntity<>(
				countryAndCityService.getCoveredCities(LocaleContextHolder.getLocale()), HttpStatus.OK);
	}
	
}
