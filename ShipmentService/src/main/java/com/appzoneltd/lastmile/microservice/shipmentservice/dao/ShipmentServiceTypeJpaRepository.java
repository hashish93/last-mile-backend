package com.appzoneltd.lastmile.microservice.shipmentservice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository : ShipmentServiceType.
 */
@Repository
public interface ShipmentServiceTypeJpaRepository extends CrudRepository<ShipmentServiceTypeEntity, Long> {
	List<ShipmentServiceTypeEntity> findByShipmentServiceId(Long shipmentServiceId);
}
