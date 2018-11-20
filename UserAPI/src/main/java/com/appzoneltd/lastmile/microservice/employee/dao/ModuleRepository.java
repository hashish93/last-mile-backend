package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.ModuleEntity;

/**
 * Repository : Module.
 */
public interface ModuleRepository extends PagingAndSortingRepository<ModuleEntity, Long> {

}
