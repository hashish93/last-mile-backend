package com.appzoneltd.lastmile.microservice.calendar.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.calendar.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingDao extends PagingAndSortingRepository<BuildingEntity, Long> {

}
