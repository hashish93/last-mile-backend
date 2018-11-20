package com.appzoneltd.lastmile.microservices.stats.reports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.GoExtraSummeryEntity;

/**
 * Repository : GoExtraSummery.
 */
public interface GoExtraSummeryRepository extends CrudRepository<GoExtraSummeryEntity, Long> {

	@Query("select SUM (g.rejectCount) from GoExtraSummeryEntity g where g.withPeriod=:withPeriod AND g.hubId IN (:hubIds)")
	public Long countRejectOndemand(@Param("hubIds") List<Long> hubIds, @Param("withPeriod") String withinPeriod);

	@Query("select SUM (g.canceledCount) from GoExtraSummeryEntity g where g.withPeriod=:withPeriod AND g.hubId IN (:hubIds)")
	public Long countCancelledOndemand(@Param("hubIds") List<Long> hubIds, @Param("withPeriod") String withinPeriod);

	@Query("select SUM (g.acknowledgeCount) from GoExtraSummeryEntity g where g.withPeriod=:withPeriod AND g.hubId IN (:hubIds)")
	public Long countAcknowledgedOndemand(@Param("hubIds") List<Long> hubIds,
			@Param("withPeriod") String withinPeriod);

}
