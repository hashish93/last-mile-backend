package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PickupRequestEntity;

public interface PickupRequestJpaRepository extends CrudRepository<PickupRequestEntity, Long> {

	@Query(value = "SELECT pt.type FROM lastmile_request.pickup_request p INNER JOIN lastmile_request.pickup_request_type pt ON p.pickup_request_type_id=pt.pickup_request_type_id WHERE p.pickup_request_id=:id", nativeQuery = true)
	String findByRequestType(@Param("id") Long requestId);

}
