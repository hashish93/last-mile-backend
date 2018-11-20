package com.appzoneltd.lastmile.microservice.hubconfig.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.hubconfig.entity.BuildingTypeEntity;

/**
 * Repository : BuildingType.
 */
public interface BuildingTypeDao extends PagingAndSortingRepository<BuildingTypeEntity, Long> {

}
