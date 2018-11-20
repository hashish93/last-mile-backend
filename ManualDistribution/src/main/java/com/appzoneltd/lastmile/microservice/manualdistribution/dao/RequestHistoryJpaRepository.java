package com.appzoneltd.lastmile.microservice.manualdistribution.dao;


import com.appzoneltd.lastmile.microservice.manualdistribution.entity.RequestHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryJpaRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {

	@Query(value = "SELECT  r  FROM RequestHistoryEntity r WHERE r.requestId=:id  ORDER BY r.created DESC")
	List<RequestHistoryEntity> getAllRequestHistory(@Param("id") Long id);
	
	@Query(value = "SELECT DISTINCT r.packageId  FROM RequestHistoryEntity r WHERE r.requestId=:id ")
	Long getPackageId(@Param("id") Long id);
	
	@Query(value = "SELECT DISTINCT r  FROM RequestHistoryEntity r WHERE r.requestId=:id")
	RequestHistoryEntity getRequestHistoryInfoByOrderId(@Param("id") Long id);

}
