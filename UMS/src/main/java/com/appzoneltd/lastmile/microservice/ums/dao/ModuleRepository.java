package com.appzoneltd.lastmile.microservice.ums.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ums.entity.ModuleEntity;

/**
 * Repository : Module.
 */
public interface ModuleRepository extends PagingAndSortingRepository<ModuleEntity, Long> {

}
