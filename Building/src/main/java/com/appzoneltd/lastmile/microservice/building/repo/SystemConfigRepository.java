package com.appzoneltd.lastmile.microservice.building.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.SystemConfigEntity;

/**
 * Repository : SystemConfig.
 */
public interface SystemConfigRepository extends PagingAndSortingRepository<SystemConfigEntity, Long> {

	@Query("SELECT s FROM SystemConfigEntity s WHERE s.superConfig=FALSE AND s.status='ACTIVE' ORDER BY s.id ASC")
	public List<SystemConfigEntity> findAllDefaultConfigurations();
	
}
