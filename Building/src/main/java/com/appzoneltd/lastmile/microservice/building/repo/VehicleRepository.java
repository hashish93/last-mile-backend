package com.appzoneltd.lastmile.microservice.building.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.VehicleEntity;

/**
 * Repository : Vehicle.
 */
public interface VehicleRepository extends PagingAndSortingRepository<VehicleEntity, Long> {

}
