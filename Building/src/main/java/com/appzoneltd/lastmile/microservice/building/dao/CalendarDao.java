package com.appzoneltd.lastmile.microservice.building.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.CalendarEntity;

/**
 * Repository : Calendar.
 */
public interface CalendarDao extends PagingAndSortingRepository<CalendarEntity, Long> {
	
	@Query("SELECT c FROM CalendarEntity c ORDER BY c.id ASC")
	List<CalendarEntity> findAllCalendars();
	
}
