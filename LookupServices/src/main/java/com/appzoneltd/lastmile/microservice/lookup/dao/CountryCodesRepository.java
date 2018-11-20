package com.appzoneltd.lastmile.microservice.lookup.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.CountryCodesEntity;

/**
 * Repository : CountryCodes.
 */
public interface CountryCodesRepository extends PagingAndSortingRepository<CountryCodesEntity, Long> {
	
	 List<CountryCodesEntity> findAllByOrderByIdAsc();

}
