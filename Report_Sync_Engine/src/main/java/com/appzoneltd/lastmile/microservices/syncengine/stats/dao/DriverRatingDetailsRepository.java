package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.DriverRatingSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.DriverRatingDetailsEntity;

/**
 * Repository : DriverRatingDetails.
 */
public interface DriverRatingDetailsRepository extends PagingAndSortingRepository<DriverRatingDetailsEntity, Long> {
	
	DriverRatingDetailsEntity findByDriverId(Long driverId);
	
	@Query("SELECT d FROM DriverRatingDetailsEntity d WHERE d.driverType = 'CORPORATE_DRIVER'")
	List<DriverRatingDetailsEntity> getCorporateDrivers();
	
	@Query("SELECT d FROM DriverRatingDetailsEntity d WHERE d.driverType = 'FREELANCER_DRIVER'")
	List<DriverRatingDetailsEntity> getFreelancerDrivers();
	
	@Query("SELECT new com.appzoneltd.lastmile.microservices.syncengine.engine.model.DriverRatingSummeryReportModel (d.hubId AS hubId,d.driverType as type ,d.driverRating as rate , count(d) AS count)  FROM DriverRatingDetailsEntity  d group by d.hubId,d.driverType , d.driverRating")
	 List<DriverRatingSummeryReportModel>  findByRateAndHubIdGrouped();

}
