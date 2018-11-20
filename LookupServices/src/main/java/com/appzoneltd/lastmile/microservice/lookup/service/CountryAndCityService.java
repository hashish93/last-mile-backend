package com.appzoneltd.lastmile.microservice.lookup.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.lookup.dao.CityRepository;
import com.appzoneltd.lastmile.microservice.lookup.dao.CountryCodesRepository;
import com.appzoneltd.lastmile.microservice.lookup.dao.CountryRepository;
import com.appzoneltd.lastmile.microservice.lookup.dao.CoveredCitiesRepository;
import com.appzoneltd.lastmile.microservice.lookup.dto.CityDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.CountryCodesDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.CountryDto;
import com.appzoneltd.lastmile.microservice.lookup.entity.CityEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.CountryEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.CoveredCitiesEntity;
import com.appzoneltd.lastmile.microservice.lookup.transformer.CountryCityTransformer;

@Service
public class CountryAndCityService {

	@Autowired
	private CountryCodesRepository countryCodesRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CoveredCitiesRepository coveredCitiesRepository;

	public List<CountryDto> getAllCountries(Locale locale) {
		List<CountryEntity> countryEntities = countryRepository.findByOrderByNameEnAsc();
		return CountryCityTransformer.getCountryDtoFromEntities(countryEntities, locale);
	}

	public CountryDto getCountryById(Long coutryId, Locale locale) {
		CountryEntity countryEntity = countryRepository.findOne(coutryId);
		return CountryCityTransformer.getCountryDtoFromEntity(countryEntity, locale);
	}
	
	public List<CountryCodesDto> getCountryCodes() {
        return CountryCityTransformer.getCountryCodeDtoFromEntities(countryCodesRepository.findAllByOrderByIdAsc());
    }
	
	public List<CityDto> getAllCities(Locale locale) {
		List<CityEntity> cityEntities = cityRepository.findAllByOrderByNameEn();
		return CountryCityTransformer.getCityDtoFromEntities(cityEntities, locale);
	}

	public CityDto getCityById(Long cityId, Locale locale) {
		CityEntity cityEntity= cityRepository.findOne(cityId);
		return CountryCityTransformer.getCityDtoFromEntity(cityEntity, locale);
	}
	
	public List<CityDto> getCitiesByCountryId(Long countryId, Locale locale) {
		List<CityEntity> cityEntities = cityRepository.getCitiesByCountryId(countryId);
		return CountryCityTransformer.getCityDtoFromEntities(cityEntities, locale);
	}

	public List<CityDto> getCoveredCities(Locale locale) {
		List<CoveredCitiesEntity> coveredCitiesEntities = coveredCitiesRepository.findAllByOrderById();
		return CountryCityTransformer.getCityDtoFromCoverCityEntities(coveredCitiesEntities, locale);

	}
	
}
