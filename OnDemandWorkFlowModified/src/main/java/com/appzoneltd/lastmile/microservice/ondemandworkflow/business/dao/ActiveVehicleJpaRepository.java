package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.ActiveVehicleEntity;

/**
 * Repository : ActiveVehicle.
 */
public interface ActiveVehicleJpaRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

}
