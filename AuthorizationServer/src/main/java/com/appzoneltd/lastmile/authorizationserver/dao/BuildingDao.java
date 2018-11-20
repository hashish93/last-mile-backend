package com.appzoneltd.lastmile.authorizationserver.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.authorizationserver.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingDao extends PagingAndSortingRepository<BuildingEntity, Long> {
	
	@Query("SELECT b FROM BuildingEntity b WHERE b.status='ACTIVE'")
	List<BuildingEntity> getAllBuilding();
	

}	
