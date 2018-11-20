package com.appzoneltd.lastmile.microservice.shipmentservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository : ShipmentService.
 */
@Repository
public interface ShipmentServiceJpaRepository extends PagingAndSortingRepository<ShipmentServiceEntity, Long> {

}
