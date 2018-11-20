package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.SystemConfigEntity;

/**
 * Repository : SystemConfig.
 */
public interface SystemConfigRepository extends PagingAndSortingRepository<SystemConfigEntity, Long> {

}
