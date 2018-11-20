package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PickupTimeEntity;

public interface PickupTimeRepository extends CrudRepository<PickupTimeEntity, Long> {

}
