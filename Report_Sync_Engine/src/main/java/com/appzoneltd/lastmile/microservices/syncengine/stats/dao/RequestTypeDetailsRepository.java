package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.RequestTypeSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.RequestTypeDetailsEntity;

/**
 * Repository : RequestTypeDetails.
 */
public interface RequestTypeDetailsRepository extends PagingAndSortingRepository<RequestTypeDetailsEntity, Long> {

	RequestTypeDetailsEntity findByRequestIdAndRequestType(Long requestId , String requestType);
	
	@Query("SELECT new com.appzoneltd.lastmile.microservices.syncengine.engine.model.RequestTypeSummeryReportModel (r.hubId AS hubId ,r.requestType AS requestType ,  count(r) AS count)  FROM RequestTypeDetailsEntity  r WHERE r.created BETWEEN  :pastToday AND :today  group by r.hubId , r.requestType")
	 List<RequestTypeSummeryReportModel>  findByHubIdAndRequestTypeGrouped(@Param("today") Date today , @Param("pastToday") Date pastDate);
}
