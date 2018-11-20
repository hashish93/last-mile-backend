package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.DriverEntity;

/**
 * Repository : Driver.
 */
public interface DriverRepository extends PagingAndSortingRepository<DriverEntity, Long> {

	@Query("SELECT d FROM DriverEntity d ORDER BY d.updated DESC")
	List<DriverEntity> getAllDriversOrdered();
	
	@Query("SELECT d FROM DriverEntity d WHERE d.updated > :updatedTime ORDER BY d.updated DESC")
	List<DriverEntity> getLastUpdatedDrivers(@Param("updatedTime") Date updatedTime);
}
