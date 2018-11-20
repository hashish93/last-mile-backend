package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.PickupOrDeliveryRequest;

public interface PickupOrDeliveryReqRepo extends CrudRepository<PickupOrDeliveryRequest, Long> {

	
	@Query(value = "SELECT DISTINCT pid FROM PickupOrDeliveryRequest pid WHERE pid.jobOrderId=:id ORDER BY pid.created DESC")
	public List<PickupOrDeliveryRequest> getOrderInfo(@Param("id") Long jobOrderId);
}
