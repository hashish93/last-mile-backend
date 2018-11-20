package com.appzoneltd.lastmile.microservice.workflow.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.workflow.business.entity.RequestHistoryEntity;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryJpaRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {
	 
//	 @Query(value = "SELECT DISTINCT request_id FROM lastmile_request.request_history WHERE  request_type='PICKUP' AND package_id=:packageId ", nativeQuery = true)
//	 Long getPackagePickupId(@Param("packageId") Long packageId);
//	 
}
