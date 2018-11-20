package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.CountryEntity;

/**
 * Repository : Country.
 */
public interface CountryJpaRepository extends PagingAndSortingRepository<CountryEntity, Long> {

}
