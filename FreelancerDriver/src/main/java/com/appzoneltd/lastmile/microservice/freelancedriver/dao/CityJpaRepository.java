package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.CityEntity;

/**
 * Repository : City.
 */
public interface CityJpaRepository extends PagingAndSortingRepository<CityEntity, Long> {

}
