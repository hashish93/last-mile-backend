package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.BuildingTypeEntity;

/**
 * Repository : BuildingType.
 */
public interface BuildingTypeRepository extends PagingAndSortingRepository<BuildingTypeEntity, Long> {

}
