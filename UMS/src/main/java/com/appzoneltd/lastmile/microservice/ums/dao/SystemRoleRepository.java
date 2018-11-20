package com.appzoneltd.lastmile.microservice.ums.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ums.entity.SystemRoleEntity;

/**
 * Repository : SystemRole.
 */
public interface SystemRoleRepository extends PagingAndSortingRepository<SystemRoleEntity, Long> {

}
