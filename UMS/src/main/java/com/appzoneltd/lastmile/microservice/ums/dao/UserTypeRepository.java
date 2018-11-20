package com.appzoneltd.lastmile.microservice.ums.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ums.entity.UserTypeEntity;

/**
 * Repository : UserType.
 */
public interface UserTypeRepository extends PagingAndSortingRepository<UserTypeEntity, Long> {

}
