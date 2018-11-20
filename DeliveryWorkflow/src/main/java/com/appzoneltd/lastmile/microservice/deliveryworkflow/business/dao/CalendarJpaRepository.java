package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.CalendarEntity;

public interface CalendarJpaRepository extends CrudRepository<CalendarEntity, Long> {

	@Query("SELECT c FROM CalendarEntity c ORDER BY c.id ASC")
	List<CalendarEntity> findAllOrderByIdAsc();

	CalendarEntity findByDayname(String day);

}
