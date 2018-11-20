package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.GoExtraSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.GoExtraDetailsEntity;

/**
 * Repository : GoExtraDetails.
 */
public interface GoExtraDetailsRepository extends PagingAndSortingRepository<GoExtraDetailsEntity, Long> {
	
	public GoExtraDetailsEntity findByRequestIdAndRequestStatus(Long requestId  , String requestStatus);
	
	@Query("SELECT g FROM GoExtraDetailsEntity g WHERE g.requestId =:requestId AND g.requestStatus <> 'CANCELED'")
	public GoExtraDetailsEntity getDetailsByRequestIdAndNotCanceled(@Param("requestId") Long requestId );
	
	@Query("SELECT new com.appzoneltd.lastmile.microservices.syncengine.engine.model.GoExtraSummeryReportModel (g.hubId AS hubId ,g.requestStatus AS requestStatus ,  count(g) AS count)  FROM GoExtraDetailsEntity g WHERE g.created BETWEEN  :pastToday AND :today  group by g.hubId , g.requestStatus")
	 List<GoExtraSummeryReportModel>  findByHubIdAndRequestStatusGrouped(@Param("today") Date today , @Param("pastToday") Date pastDate);
	
}
