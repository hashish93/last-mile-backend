package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.DriverEntity;

public interface DriverRepository extends CrudRepository<DriverEntity, Long> {

	@Query("SELECT COUNT(x) from DriverEntity x  WHERE  ( x.rating > 0 and x.rating <= 1) ")
	public Long countCorporateDriverRatingOneStar();

	@Query("SELECT COUNT(x) from DriverEntity x  WHERE  ( x.rating > 1 and x.rating <= 2) ")
	public Long countCorporateDriverRatingTwoStars();

	@Query("SELECT COUNT(x) from DriverEntity x  WHERE ( x.rating > 2 and x.rating <= 3) ")
	public Long countCorporateDriverRatingThreeStars();

	@Query("SELECT COUNT(x) from DriverEntity x  WHERE  ( x.rating > 3 and x.rating <= 4) ")
	public Long countCorporateDriverRatingFourStars();

	@Query("SELECT COUNT(x) from DriverEntity x  WHERE  ( x.rating > 4 and x.rating <= 5) ")
	public Long countCorporateDriverRatingFiveStars();
	
	
	@Query("select COUNT (d) from DriverEntity d inner join d.user u inner join u.userHubs uh inner join uh.building b where b.buildingId IN (:hubIds) AND ( d.rating > 0 and d.rating <= 1)")
	public Long getCountCorporateDriverRatingOneStarsToHub(@Param("hubIds") List<Long> hubIds);

	@Query("select COUNT (d) from DriverEntity d inner join d.user u inner join u.userHubs uh inner join uh.building b where b.buildingId IN (:hubIds) AND ( d.rating > 1 and d.rating <= 2)")
	public Long getCountCorporateDriverRatingTwoStarsToHub(@Param("hubIds") List<Long> hubIds);
	
	@Query("select COUNT (d) from DriverEntity d inner join d.user u inner join u.userHubs uh inner join uh.building b where b.buildingId IN (:hubIds) AND ( d.rating > 2 and d.rating <= 3)")
	public Long getCountCorporateDriverRatingThreeStarsToHub(@Param("hubIds") List<Long> hubIds);
	
	@Query("select COUNT (d) from DriverEntity d inner join d.user u inner join u.userHubs uh inner join uh.building b where b.buildingId IN (:hubIds) AND ( d.rating > 3 and d.rating <= 4)")
	public Long getCountCorporateDriverRatingFourStarsToHub(@Param("hubIds") List<Long> hubIds);
	
	@Query("select COUNT (d) from DriverEntity d inner join d.user u inner join u.userHubs uh inner join uh.building b where b.buildingId IN (:hubIds) AND ( d.rating > 4 and d.rating <= 5)")
	public Long getCountCorporateDriverRatingFiveStarsToHub(@Param("hubIds") List<Long> hubIds);
}
