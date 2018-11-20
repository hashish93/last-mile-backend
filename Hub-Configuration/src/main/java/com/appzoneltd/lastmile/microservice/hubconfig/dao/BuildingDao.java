package com.appzoneltd.lastmile.microservice.hubconfig.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.hubconfig.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingDao extends PagingAndSortingRepository<BuildingEntity, Long> {

}
