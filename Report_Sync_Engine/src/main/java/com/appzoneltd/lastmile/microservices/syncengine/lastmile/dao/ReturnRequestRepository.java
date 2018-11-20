package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.ReturnRequestEntity;

/**
 * Repository : ReturnRequest.
 */
public interface ReturnRequestRepository extends PagingAndSortingRepository<ReturnRequestEntity, Long> {

	@Query(value = "SELECT r FROM ReturnRequestEntity r ORDER BY r.updated DESC")
	List<ReturnRequestEntity> getAllReturnOrdered();
	
	@Query(value = "SELECT r  FROM ReturnRequestEntity r WHERE r.updated > :updatedTime ORDER BY r.updated DESC")
	List<ReturnRequestEntity> getLastUpdatedAllReturnOrdered(@Param("updatedTime") Date updatedTime);
}
