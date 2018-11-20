package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.ShipmentServiceEntity;

/**
 * Repository : ShipmentService.
 */
public interface ShipmentServiceRepository extends PagingAndSortingRepository<ShipmentServiceEntity, Long> {

}
