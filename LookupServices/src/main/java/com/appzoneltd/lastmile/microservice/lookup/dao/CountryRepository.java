package com.appzoneltd.lastmile.microservice.lookup.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.CountryEntity;

/**
 * Repository : Country.
 */
public interface CountryRepository extends PagingAndSortingRepository<CountryEntity, Long> {

	public List<CountryEntity> findByOrderByNameEnAsc();
	
}
