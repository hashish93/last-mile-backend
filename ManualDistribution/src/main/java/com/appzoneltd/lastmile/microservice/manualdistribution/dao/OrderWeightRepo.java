package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.OrderWeight;

public interface OrderWeightRepo extends CrudRepository<OrderWeight, Long> {

	@Query(value = "SELECT pw FROM OrderWeight pw WHERE pw.packageId=:id")
	public OrderWeight JobOrderWeight(@Param("id") Long packageId);

}
