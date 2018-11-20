package com.appzoneltd.lastmile.microservice.lookup.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.ShipmentServiceEntity;

/**
 * Repository : ShipmentService.
 */
public interface ShipmentServiceRepository extends PagingAndSortingRepository<ShipmentServiceEntity, Long> {	
	
}
