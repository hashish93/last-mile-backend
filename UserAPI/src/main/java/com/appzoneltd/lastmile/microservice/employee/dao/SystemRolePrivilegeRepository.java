package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.SystemRolePrivilegeEntity;

/**
 * Repository : SystemRolePrivilege.
 */
public interface SystemRolePrivilegeRepository extends PagingAndSortingRepository<SystemRolePrivilegeEntity, Long> {

}
