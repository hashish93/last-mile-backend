package com.appzoneltd.lastmile.microservice.countryandcity.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.countryandcity.dao.CityEntity;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CityJpaRepository;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CoveredCityEntity;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CoveredCityJpaRepository;
import com.appzoneltd.lastmile.microservice.countryandcity.dto.City;
import com.appzoneltd.lastmile.microservice.countryandcity.dto.Mapper;

/**
 * @author alaa.nabil
 *
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

	private final CityJpaRepository cityJpaRepository;
	private final CoveredCityJpaRepository coveredCityJpaRepository;

	@Autowired
	public CityServiceImpl(CityJpaRepository cityJpaRepository , 
			CoveredCityJpaRepository coveredCityJpaRepository) {
		
		this.cityJpaRepository = cityJpaRepository;
		this.coveredCityJpaRepository = coveredCityJpaRepository;
	}

	@Override
	public List<City> getAllCities(Locale locale) {
		return Mapper.cityMapper((List<CityEntity>) cityJpaRepository.findAllByOrderByNameEn(), locale);
	}

	@Override
	public City getCityById(Long id, Locale locale) {
		return Mapper.cityMapper(cityJpaRepository.findOne(id), locale);
	}

	@Override
	public List<City> getCitiesByCountryId(Long countryId, Locale locale) {
		return Mapper.cityMapper(cityJpaRepository.getCitiesByCountryIdOrderByNameEn(countryId), locale);
	}

	@Override
	public List<City> getCoveredCities(Locale locale) {
		
		return Mapper.cityMapperFromCoveredCities(
				(List<CoveredCityEntity>) coveredCityJpaRepository.findAllByOrderByNameEn(), 
				locale);
	}

}
