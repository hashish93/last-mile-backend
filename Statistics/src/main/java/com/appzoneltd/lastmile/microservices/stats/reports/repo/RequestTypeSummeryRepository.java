package com.appzoneltd.lastmile.microservices.stats.reports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.RequestTypeSummeryEntity;

/**
 * Repository : RequestTypeSummery.
 */
public interface RequestTypeSummeryRepository extends CrudRepository<RequestTypeSummeryEntity, Long> {
	
	@Query("select SUM (rt.onDemandCount) from RequestTypeSummeryEntity rt where rt.withinPeriod=:withinPeriod AND rt.hubId IN (:hubIds)")
	public Long fetchTotalOnDemandPickups(@Param("hubIds") List<Long> hubIds , @Param("withinPeriod") String withinPeriod);
	
	
	@Query("select SUM (rt.scheduledCount) from RequestTypeSummeryEntity rt where rt.withinPeriod=:withinPeriod AND rt.hubId IN (:hubIds)")
	public Long fetchTotalScheduledPickups(@Param("hubIds") List<Long> hubIds , @Param("withinPeriod") String withinPeriod);
	
	
	@Query("select SUM (rt.deliveryCount) from RequestTypeSummeryEntity rt where rt.withinPeriod=:withinPeriod AND rt.hubId IN (:hubIds)")
	public Long fetchTotalDeliveries(@Param("hubIds") List<Long> hubIds , @Param("withinPeriod") String withinPeriod);
	
	
	@Query("select SUM (rt.returnCount) from RequestTypeSummeryEntity rt where rt.withinPeriod=:withinPeriod AND rt.hubId IN (:hubIds)")
	public Long fetchTotalReturns(@Param("hubIds") List<Long> hubIds , @Param("withinPeriod") String withinPeriod);

}
