package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.pickupStatisticsSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PickupStatisticsDetailsEntity;

/**
 * Repository : PickupStatisticsDetails.
 */
public interface PickupStatisticsDetailsRepository extends PagingAndSortingRepository<PickupStatisticsDetailsEntity, Long> {

	PickupStatisticsDetailsEntity findByRequestId(Long requestId);
	
	@Query("SELECT new com.appzoneltd.lastmile.microservices.syncengine.engine.model.pickupStatisticsSummeryReportModel (p.hubId AS hubId ,p.requestType AS requestType ,p.requestStatus AS requestStatus ,  count(p) AS count)  FROM PickupStatisticsDetailsEntity p WHERE p.created BETWEEN  :pastToday AND :today  group by p.hubId , p.requestType , p.requestStatus")
	List<pickupStatisticsSummeryReportModel> findByHubIdAndRequestTypeAndRequestStatusGrouped(@Param("today") Date today , @Param("pastToday") Date pastDate);
	
}
