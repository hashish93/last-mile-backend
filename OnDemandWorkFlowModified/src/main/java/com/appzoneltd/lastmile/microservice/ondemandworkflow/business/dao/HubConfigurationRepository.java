package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.HubConfigurationEntity;

/**
 * Repository : HubConfiguration.
 */
public interface HubConfigurationRepository extends PagingAndSortingRepository<HubConfigurationEntity, Long> {

	@Query("SELECT h FROM HubConfigurationEntity h inner join h.building b inner join h.systemConfig s WHERE b.buildingId = :hubId AND s.status='ACTIVE' ORDER BY s.id ASC")
	public List<HubConfigurationEntity> findAllConfigurationForHub(@Param("hubId") Long hubId);
		
	@Query("SELECT h FROM HubConfigurationEntity h inner join h.building b inner join h.systemConfig s WHERE b.buildingId = :hubId AND s.status='ACTIVE' AND s.configId= :systemConfigId")
	public List<HubConfigurationEntity> findSystemConfigurationForHub(@Param("hubId") Long hubId , @Param("systemConfigId") Long systemConfigId);
	
}
