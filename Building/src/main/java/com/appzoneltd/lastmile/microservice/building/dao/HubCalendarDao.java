package com.appzoneltd.lastmile.microservice.building.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.building.entity.HubCalendarEntity;

/**
 * Repository : HubCalendar.
 */
public interface HubCalendarDao extends PagingAndSortingRepository<HubCalendarEntity, Long> {

	@Query("SELECT h FROM HubCalendarEntity h inner join h.building b WHERE b.buildingId=:hubId ORDER BY h.id ASC")
	public List<HubCalendarEntity> findAllHubCalendar(@Param("hubId") Long hubId);

	@Query("SELECT h FROM HubCalendarEntity h inner join h.building b inner join h.calendar c WHERE b.buildingId=:hubId AND c.id = :calendarId")
	public HubCalendarEntity findHubCalendarwithCalendarId(@Param("calendarId") Long calendarId,
			@Param("hubId") Long hubId);

}
