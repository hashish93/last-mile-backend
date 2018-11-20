package com.appzoneltd.lastmile.microservice.ums.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ums.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, Long> {
	
	@Query("SELECT DISTINCT b FROM BuildingEntity b WHERE b.status='ACTIVE' AND b.buildingId =:hubId")
	BuildingEntity getBuildingById(@Param("hubId") Long hubId);
	
	@Query("SELECT b FROM BuildingEntity b WHERE b.status='ACTIVE'")
	List<BuildingEntity> getAllBuilding();
	

}
