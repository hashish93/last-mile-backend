package com.appzoneltd.lastmile.microservice.ums.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ums.entity.PrivilegeEntity;

/**
 * Repository : Privilege.
 */
public interface PrivilegeRepository extends PagingAndSortingRepository<PrivilegeEntity, Long> {

}
