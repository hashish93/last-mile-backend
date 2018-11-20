package com.appzoneltd.lastmile.microservice.activevehicle.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : Building.
 */
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, Long> {

	@Query("SELECT b FROM BuildingEntity b WHERE b.buildingId IN (:hubs)")
	public Page<BuildingEntity> getSelectedHubs(@Param("hubs") List<Long> hubs,Pageable pageable);
	
	@Query("SELECT b FROM BuildingEntity b WHERE b.buildingId  <> :hubId AND b.buildingname = :name")
	public List<BuildingEntity> getBuildingByName(@Param("hubId") Long hubId,@Param("name") String name);
}
