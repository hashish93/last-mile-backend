package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.PrivilegeEntity;

/**
 * Repository : Privilege.
 */
public interface PrivilegeRepository extends PagingAndSortingRepository<PrivilegeEntity, Long> {

}
