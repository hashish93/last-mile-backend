package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.UserEntity;

/**
 * Repository : Users.
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

}
