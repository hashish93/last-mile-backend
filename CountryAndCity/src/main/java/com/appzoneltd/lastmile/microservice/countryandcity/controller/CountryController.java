package com.appzoneltd.lastmile.microservice.countryandcity.controller;

import java.util.List;

import com.appzoneltd.lastmile.microservice.countryandcity.dto.CountryCode;
import com.appzoneltd.lastmile.microservice.countryandcity.service.CountryCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.countryandcity.dto.Country;
import com.appzoneltd.lastmile.microservice.countryandcity.service.CountryService;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class CountryController {

	private final CountryService countryService;
	private final CountryCodeService countryCodeService;

	@Autowired
	public CountryController(CountryService countryService, CountryCodeService countryCodeService) {
		this.countryService = countryService;
		this.countryCodeService = countryCodeService;
	}

	@RequestMapping(value = ("/countries"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> getCountries() {
		return new ResponseEntity<List<Country>>(countryService.getAllCountries(LocaleContextHolder.getLocale()),
				HttpStatus.OK);
	}

	@RequestMapping(value = ("/countryCodes"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CountryCode>> getCountryCodes() {
		return new ResponseEntity<List<CountryCode>>(countryCodeService.getCountryCodes(),
				HttpStatus.OK);
	}
}
