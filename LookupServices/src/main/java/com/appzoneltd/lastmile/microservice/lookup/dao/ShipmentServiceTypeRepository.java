package com.appzoneltd.lastmile.microservice.lookup.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.lookup.entity.ShipmentServiceTypeEntity;

/**
 * Repository : ShipmentServiceType.
 */
public interface ShipmentServiceTypeRepository extends PagingAndSortingRepository<ShipmentServiceTypeEntity, Long> {

	@Query("SELECT s FROM ShipmentServiceTypeEntity s inner join s.shipmentService ss where ss.shipmentServiceId= :shipmentServiceId")
	public List<ShipmentServiceTypeEntity> findByShipmentServiceId(@Param("shipmentServiceId") Long shipmentServiceId);
	
}
