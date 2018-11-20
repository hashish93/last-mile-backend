package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.RolePrivilegeEntity;

/**
 * Repository : RolePrivilege.
 */
public interface RolePrivilegeRepository extends PagingAndSortingRepository<RolePrivilegeEntity, Long> {

	
}
