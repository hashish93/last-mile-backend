package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.ActiveVehicleEntity;

/**
 * Repository : ActiveVehicle.
 */
public interface ActiveVehicleJpaRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

}
