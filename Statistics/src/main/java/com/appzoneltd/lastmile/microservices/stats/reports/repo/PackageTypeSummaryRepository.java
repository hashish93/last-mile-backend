package com.appzoneltd.lastmile.microservices.stats.reports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.PackageTypeSummaryEntity;

/**
 * Repository : PackageTypeSummary.
 */
public interface PackageTypeSummaryRepository extends CrudRepository<PackageTypeSummaryEntity, Long> {

	@Query(value = "SELECT DISTINCT p.packageTypeId FROM PackageTypeSummaryEntity p")
	List<Long> findAllPackageTypeIds();

	@Query("select pt from PackageTypeSummaryEntity pt where pt.withinPeriod=:withinPeriod AND pt.hubId IN (:hubIds) AND  pt.packageTypeId IN (:packageTypeIds)")
	List<PackageTypeSummaryEntity> findAll(@Param("withinPeriod") String withinPeriod,
			@Param("hubIds") List<Long> hubIds, @Param("packageTypeIds") List<Long> packageTypeIds);

}
