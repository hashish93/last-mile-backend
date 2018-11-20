package com.appzoneltd.lastmile.microservice.returnworkflow.business.couchbase.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.couchbase.model.ActiveVehicleModel;

@N1qlPrimaryIndexed
public interface ActiveVehiclePersistRepository extends CrudRepository<ActiveVehicleModel, Long> {
	
}
