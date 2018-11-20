package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.PackageTypeSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PackageTypeDetailsEntity;

/**
 * Repository : PackageTypeDetails.
 */
public interface PackageTypeDetailsRepository extends CrudRepository<PackageTypeDetailsEntity, Long> {

	PackageTypeDetailsEntity findByPackageId(Long packageId);
	
	@Query("SELECT new com.appzoneltd.lastmile.microservices.syncengine.engine.model.PackageTypeSummeryReportModel (p.hubId AS hubId ,p.packageTypeId AS packageTypeId ,p.packageValue AS packageValue ,  count(p) AS count)  FROM PackageTypeDetailsEntity  p WHERE p.created BETWEEN  :pastToday AND :today  group by p.hubId , p.packageTypeId , p.packageValue")
	 List<PackageTypeSummeryReportModel>  findByHubIdAndPackageTypeIdGrouped(@Param("today") Date today , @Param("pastToday") Date pastDate);
}
