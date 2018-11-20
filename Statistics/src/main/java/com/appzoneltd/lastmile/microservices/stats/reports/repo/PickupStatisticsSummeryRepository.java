package com.appzoneltd.lastmile.microservices.stats.reports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.PickupStatisticsSummeryEntity;

/**
 * Repository : PickupStatisticsSummery.
 */
public interface PickupStatisticsSummeryRepository extends CrudRepository<PickupStatisticsSummeryEntity, Long> {

	@Query("SELECT SUM (x.pickedupCount) from PickupStatisticsSummeryEntity x  WHERE x.withinPeriod =:withinPeriod  and x.requestType=:requestType and x.hubId IN (:hubIds)")
	public Long countSucceededPickupOrder(@Param("withinPeriod") String withinPeriod, @Param("requestType") String requestType,@Param("hubIds") List<Long> hubIds);
	
	@Query("SELECT SUM (x.canceledCount) from PickupStatisticsSummeryEntity x  WHERE x.withinPeriod =:withinPeriod and x.requestType=:requestType  and x.hubId IN (:hubIds)")
	public Long countCancelledPickupOrder(@Param("withinPeriod") String withinPeriod, @Param("requestType") String requestType,@Param("hubIds") List<Long> hubIds);
	
	@Query("SELECT SUM (x.noCapacityCount) from PickupStatisticsSummeryEntity x  WHERE x.withinPeriod =:withinPeriod and x.requestType=:requestType  and x.hubId IN (:hubIds)")
	public Long countNoCapacityPickupOrder(@Param("withinPeriod") String withinPeriod,@Param("requestType") String requestType ,@Param("hubIds") List<Long> hubIds);
	
	@Query("SELECT SUM (x.noCoverageCount) from PickupStatisticsSummeryEntity x  WHERE x.withinPeriod =:withinPeriod and x.requestType=:requestType")
	public Long countNoCoveragePickupOrder(@Param("withinPeriod") String withinPeriod ,@Param("requestType") String requestType);
	
	
	
}
