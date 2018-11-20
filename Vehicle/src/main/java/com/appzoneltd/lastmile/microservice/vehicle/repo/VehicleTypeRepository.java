package com.appzoneltd.lastmile.microservice.vehicle.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.vehicle.entity.VehicleTypeEntity;

/**
 * Repository : VehicleType.
 */
public interface VehicleTypeRepository extends PagingAndSortingRepository<VehicleTypeEntity, Long> {

}
