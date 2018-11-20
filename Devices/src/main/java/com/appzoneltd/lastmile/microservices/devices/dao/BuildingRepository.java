package com.appzoneltd.lastmile.microservices.devices.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.devices.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, Long> {

	@Query("SELECT b FROM BuildingEntity b WHERE b.status = 'ACTIVE'")
	public List<BuildingEntity> getActiveBuildings();
	
	@Query("SELECT b FROM BuildingEntity b WHERE b.status = 'ACTIVE' AND b.buildingId =:hubId")
	public BuildingEntity getActiveBuildingById(@Param("hubId") Long hubId);
}
