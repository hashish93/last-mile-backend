package com.appzoneltd.lastmile.microservice.calendar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.calendar.entity.CalendarEntity;

/**
 * Repository : Calendar.
 */
public interface CalendarDao extends PagingAndSortingRepository<CalendarEntity, Long> {
	
	@Query("SELECT c FROM CalendarEntity c ORDER BY c.id ASC")
	List<CalendarEntity> findAllCalendars();
	
}
