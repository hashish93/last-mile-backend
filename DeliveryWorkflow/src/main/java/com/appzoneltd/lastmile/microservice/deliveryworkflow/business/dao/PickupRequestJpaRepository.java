package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PickupRequestEntity;

/**
 * Repository : PickupRequest.
 */
public interface PickupRequestJpaRepository extends CrudRepository<PickupRequestEntity, Long> {

	
	@Query("SELECT p FROM PickupRequestEntity p WHERE p.pickupdate=:date AND p.requestStatus='SCHEDULED_FOR_PICKUP'")
	List<PickupRequestEntity> getAllScheduledForPickup(@Param("date") Date date);
	
	@Query("SELECT p FROM PickupRequestEntity p WHERE p.pickupdate=:date AND p.requestStatus='SCHEDULED_FOR_PICKUP' AND p.timeFrom =:timeFrom AND p.timeTo =:timeTo")
	List<PickupRequestEntity> getAllScheduledForPickupWithTimes(@Param("date") Date date,@Param("timeFrom") String timeFrom,@Param("timeTo") String timeTo);
}
