package com.appzoneltd.lastmile.microservice.countryandcity.service;

import java.util.List;
import java.util.Locale;

import com.appzoneltd.lastmile.microservice.countryandcity.dto.City;

public interface CityService {

	List<City> getAllCities(Locale locale);

	List<City> getCitiesByCountryId(Long countryId, Locale locale);

	City getCityById(Long id, Locale locale);

	List<City> getCoveredCities(Locale locale);
}
