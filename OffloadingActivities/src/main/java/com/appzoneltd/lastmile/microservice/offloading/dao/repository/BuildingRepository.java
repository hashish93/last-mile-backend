package com.appzoneltd.lastmile.microservice.offloading.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.offloading.dao.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, Long> {

	@Query("SELECT b FROM BuildingEntity b WHERE b.buildingId IN (:hubs)")
	public Page<BuildingEntity> getSelectedHubs(@Param("hubs") List<Long> hubs,Pageable pageable);
	
	@Query("SELECT b FROM BuildingEntity b WHERE b.buildingId  <> :hubId AND b.buildingname = :name")
	public List<BuildingEntity> getBuildingByName(@Param("hubId") Long hubId,@Param("name") String name);
	
	@Query("SELECT b FROM BuildingEntity b WHERE b.status ='ACTIVE'")
	public List<BuildingEntity> getActiveHubs();
}
