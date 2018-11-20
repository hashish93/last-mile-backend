package com.appzoneltd.lastmile.microservice.workshift.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 * @author alaa.nabil
 *
 */
public interface WorkShiftRepository extends CrudRepository<WorkShiftEntity, Long> {
	
	@Query("SELECT s FROM WorkShiftEntity s ORDER BY s.created ASC")
	List<WorkShiftEntity> findAllWorkShifts();

}
