package com.appzoneltd.lastmile.microservice.building.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.BuildingTypeEntity;

/**
 * Repository : BuildingType.
 */
public interface BuildingTypeRepository extends PagingAndSortingRepository<BuildingTypeEntity, Long> {

}
