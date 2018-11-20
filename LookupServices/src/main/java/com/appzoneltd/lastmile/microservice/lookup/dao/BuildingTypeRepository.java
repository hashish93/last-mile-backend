package com.appzoneltd.lastmile.microservice.lookup.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.BuildingTypeEntity;

/**
 * Repository : BuildingType.
 */
public interface BuildingTypeRepository extends PagingAndSortingRepository<BuildingTypeEntity, Long> {

}
