package com.appzoneltd.lastmile.microservice.countryandcity.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.countryandcity.dao.CountryEntity;
import com.appzoneltd.lastmile.microservice.countryandcity.dao.CountryJpaRepository;
import com.appzoneltd.lastmile.microservice.countryandcity.dto.Country;
import com.appzoneltd.lastmile.microservice.countryandcity.dto.Mapper;

/**
 * @author alaa.nabil
 *
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	private final CountryJpaRepository countryJpaRepository;

	@Autowired
	public CountryServiceImpl(CountryJpaRepository countryJpaRepository) {
		this.countryJpaRepository = countryJpaRepository;
	}

	@Override
	public List<Country> getAllCountries(Locale locale) {
		return Mapper.countryMapper((List<CountryEntity>) countryJpaRepository.findByOrderByNameEnAsc(), locale);
	}

	@Override
	public Country getCountryById(Long id, Locale locale) {
		return Mapper.countryMapper(countryJpaRepository.findOne(id), locale);
	}

}
