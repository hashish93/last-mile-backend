package com.appzoneltd.lastmile.microservice.calendar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.calendar.entity.WorkShiftEntity;

/**
 * Repository : WorkShift.
 */
public interface WorkShiftDao extends PagingAndSortingRepository<WorkShiftEntity, Long> {

	@Query("SELECT w FROM WorkShiftEntity w ORDER BY w.created ASC")
	List<WorkShiftEntity> findAllWorkShifts();
	
	@Query("SELECT w FROM WorkShiftEntity w inner join w.building b WHERE b.buildingId =:hubId ORDER BY w.created ASC")
	List<WorkShiftEntity> findAllWorkShiftsForHubId(@Param("hubId") Long hubId);
	
	
}
