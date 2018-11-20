package com.appzoneltd.lastmile.microservice.lookup.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.CoveredCitiesEntity;



/**
 * Repository : CoveredCities.
 */
public interface CoveredCitiesRepository extends PagingAndSortingRepository<CoveredCitiesEntity, Integer> {
	List<CoveredCitiesEntity> findAllByOrderById();

}
