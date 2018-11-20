package com.appzoneltd.lastmile.microservice.building.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.CountryEntity;

/**
 * Repository : Country.
 */
public interface CountryRepository extends PagingAndSortingRepository<CountryEntity, Long> {

}
