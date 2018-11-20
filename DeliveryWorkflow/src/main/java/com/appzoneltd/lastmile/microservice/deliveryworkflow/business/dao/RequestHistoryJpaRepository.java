package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.RequestHistoryEntity;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryJpaRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {

	@Query(value = "SELECT  r  FROM RequestHistoryEntity r WHERE r.requestId=:id  ORDER BY r.created DESC")
	List<RequestHistoryEntity> getAllRequestHistory(@Param("id") Long id);
	
	@Query(value = "SELECT r  FROM RequestHistoryEntity r WHERE r.packageId=:packageId AND requestType=:requestType ORDER BY r.created DESC")
	List<RequestHistoryEntity> getRequestsForPackage(@Param("packageId") Long packageId , @Param("requestType") String requestType);

	 @Query(value = "SELECT DISTINCT request_id FROM lastmile_request.request_history WHERE  request_type='DELIVERY' AND package_id=:packageId ", nativeQuery = true)
	 Long getPackageDeliveryId(@Param("packageId") Long packageId);
	 
	 @Query(value = "SELECT DISTINCT request_id FROM lastmile_request.request_history WHERE  request_type='PICKUP' AND package_id=:packageId ", nativeQuery = true)
	 Long getPackagePickupId(@Param("packageId") Long packageId);

	 @Query(value = "SELECT DISTINCT package_id FROM lastmile_request.request_history WHERE  request_type='DELIVERY' AND request_id=:requestId ", nativeQuery = true)
	 Long getDeliveryPackageId(@Param("requestId") Long requestId);
	 
}
