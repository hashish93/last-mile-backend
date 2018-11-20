package com.appzoneltd.lastmile.microservice.countryandcity.service;

import java.util.List;
import java.util.Locale;

import com.appzoneltd.lastmile.microservice.countryandcity.dto.Country;

/**
 * @author alaa.nabil
 *
 */
public interface CountryService {

	List<Country> getAllCountries(Locale locale);

	Country getCountryById(Long id, Locale locale);
}
