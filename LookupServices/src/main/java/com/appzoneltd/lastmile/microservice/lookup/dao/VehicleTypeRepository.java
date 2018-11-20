package com.appzoneltd.lastmile.microservice.lookup.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.VehicleTypeEntity;

/**
 * Repository : VehicleType.
 */
public interface VehicleTypeRepository extends PagingAndSortingRepository<VehicleTypeEntity, Long> {

}
