package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.SystemRoleEntity;

/**
 * Repository : SystemRole.
 */
public interface SystemRoleRepository extends PagingAndSortingRepository<SystemRoleEntity, Long> {

}
