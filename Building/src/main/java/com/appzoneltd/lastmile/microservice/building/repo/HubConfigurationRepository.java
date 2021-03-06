package com.appzoneltd.lastmile.microservice.building.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.HubConfigurationEntity;

/**
 * Repository : HubConfiguration.
 */
public interface HubConfigurationRepository extends PagingAndSortingRepository<HubConfigurationEntity, Long> {

}
