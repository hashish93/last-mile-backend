package com.appzoneltd.lastmile.microservice.hubconfig.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.hubconfig.entity.SystemConfigEntity;

/**
 * Repository : SystemConfig.
 */
public interface SystemConfigDao extends PagingAndSortingRepository<SystemConfigEntity, Long> {

	@Query("SELECT s FROM SystemConfigEntity s WHERE s.superConfig=TRUE AND s.status='ACTIVE' ORDER BY s.id ASC")
	public List<SystemConfigEntity> findAllSharedConfigurations();
	
	@Query("SELECT s FROM SystemConfigEntity s WHERE s.superConfig=TRUE AND s.status='ACTIVE' AND s.configId = :configId")
	public SystemConfigEntity findSharedConfiugrationwithConfig(@Param("configId") Long configId);
	
}
