package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.FreelancerDriverEntity;

/**
 * Repository : FreelancerDriver.
 */
public interface FreelancerDriverRepository extends PagingAndSortingRepository<FreelancerDriverEntity, Long> {
	
	@Query("SELECT f FROM FreelancerDriverEntity f ORDER BY f.updated DESC")
	List<FreelancerDriverEntity> getAllFreelancerDriversOrdered();
	
	
	@Query("SELECT f FROM FreelancerDriverEntity f WHERE f.updated > :updatedTime ORDER BY f.updated DESC")
	List<FreelancerDriverEntity> getLastUpdatedFreelancerDrivers(@Param("updatedTime") Date updatedTime);
}
