package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryJpaRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {

	@Query(value = "SELECT  r  FROM RequestHistoryEntity r WHERE r.requestId=:id AND r.requestStatus <>'' ORDER BY r.created DESC")
	List<RequestHistoryEntity> getAllRequestHistory(@Param("id") Long id);
	
	@Query(value = "SELECT  r  FROM RequestHistoryEntity r WHERE r.requestId=:id AND r.requestStatus <>'' AND r.requestType =:requestType ORDER BY r.created DESC")
	List<RequestHistoryEntity> getAllRequestHistoryWithType(@Param("id") Long id , @Param ("requestType") String requestType);
	
	@Query(value = "SELECT DISTINCT r.packageId  FROM RequestHistoryEntity r WHERE r.requestId=:id ")
	Long getPackageId(@Param("id") Long id);
	
	@Query(value = "SELECT DISTINCT r  FROM RequestHistoryEntity r WHERE r.requestId=:id")
	RequestHistoryEntity getRequestHistoryInfoByOrderId(@Param("id") Long id);

}
