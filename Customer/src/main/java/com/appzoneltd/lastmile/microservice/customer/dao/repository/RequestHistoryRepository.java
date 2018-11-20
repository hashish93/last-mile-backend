package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.RequestHistoryEntity;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {

	@Query(value = "SELECT  r  FROM RequestHistoryEntity r WHERE r.requestId=:id  ORDER BY r.created DESC")
	List<RequestHistoryEntity> getAllRequestHistory(@Param("id") Long id);
	
	@Query(value = "SELECT DISTINCT r.packageId  FROM RequestHistoryEntity r WHERE r.requestId=:id ")
	Long getPackageId(@Param("id") Long id);
	
	@Query(value = "SELECT DISTINCT r.requestId  FROM RequestHistoryEntity r WHERE r.packageId=:packageId ")
	Long getRequestIdFromPackageId(@Param("packageId") Long packageId);
	
	@Query(value = "SELECT DISTINCT r  FROM RequestHistoryEntity r WHERE r.requestId=:id")
	RequestHistoryEntity getRequestHistoryInfoByOrderId(@Param("id") Long id);

}
