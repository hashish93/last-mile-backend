package com.appzoneltd.lastmile.microservice.building.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.CityEntity;

/**
 * Repository : City.
 */
public interface CityRepository extends PagingAndSortingRepository<CityEntity, Long> {

}
