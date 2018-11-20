package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingDao extends PagingAndSortingRepository<BuildingEntity, Long> {

}
