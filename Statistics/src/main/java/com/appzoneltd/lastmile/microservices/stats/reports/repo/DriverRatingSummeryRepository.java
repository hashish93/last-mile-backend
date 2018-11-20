package com.appzoneltd.lastmile.microservices.stats.reports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.DriverRatingSummeryEntity;

/**
 * Repository : DriverRatingSummery.
 */
public interface DriverRatingSummeryRepository extends CrudRepository<DriverRatingSummeryEntity, Long> {

	@Query("SELECT SUM (x.oneStar) from DriverRatingSummeryEntity x  WHERE x.driverType=:driverType  and x.hubId IN (:hubIds)")
	public Long countDriverRatingOneStar(@Param("driverType") String type, @Param("hubIds") List<Long> hubIds);

	@Query("SELECT SUM(x.twoStar) from DriverRatingSummeryEntity x  WHERE x.driverType=:driverType and x.hubId IN (:hubIds) ")
	public Long countDriverRatingTwoStars(@Param("driverType") String type,
			@Param("hubIds") List<Long> hubIds);

	@Query("SELECT SUM (x.threeStar) from DriverRatingSummeryEntity x  WHERE x.driverType=:driverType and x.hubId IN (:hubIds) ")
	public Long countDriverRatingThreeStars(@Param("driverType") String type,
			@Param("hubIds") List<Long> hubIds);

	@Query("SELECT SUM (x.fourStar) from DriverRatingSummeryEntity x  WHERE x.driverType=:driverType and x.hubId IN (:hubIds) ")
	public Long countDriverRatingFourStars(@Param("driverType") String type,
			@Param("hubIds") List<Long> hubIds);

	@Query("SELECT SUM (x.fiveStar) from DriverRatingSummeryEntity x  WHERE  x.driverType=:driverType and x.hubId IN (:hubIds) ")
	public Long countDriverRatingFiveStars(@Param("driverType") String type,
			@Param("hubIds") List<Long> hubIds);

}
