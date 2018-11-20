package com.appzoneltd.lastmile.microservice.building.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.building.entity.BuildingServingAreaEntity;

/**
 * Repository : BuildingServingArea.
 */
public interface BuildingServingAreaRepository extends PagingAndSortingRepository<BuildingServingAreaEntity, Long> {
	
	@Query("SELECT bs FROM BuildingServingAreaEntity bs inner join bs.building b WHERE b.buildingId =:hubId")
	public List<BuildingServingAreaEntity> findAllByBuildingId(@Param("hubId") Long hubId);

}
