package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;

/**
 * Repository : DeliveryRequest.
 */
public interface DeliveryRequestRepository extends CrudRepository<DeliveryRequestEntity, Long> {
	
	
	@Query("SELECT d FROM DeliveryRequestEntity d WHERE d.deliverydate=:date AND d.requestStatus='SCHEDULED_FOR_DELIVERY'")
	List<DeliveryRequestEntity> getAllScheduledForDelivery(@Param("date") Date date);
	
	@Query("SELECT d FROM DeliveryRequestEntity d WHERE d.deliverydate=:date AND d.requestStatus='SCHEDULED_FOR_DELIVERY' AND d.timeFrom =:timeFrom AND d.timeTo =:timeTo ")
	List<DeliveryRequestEntity> getAllScheduledForDeliveryWithTimes(@Param("date") Date date,@Param("timeFrom") String timeFrom,@Param("timeTo") String timeTo);

}
