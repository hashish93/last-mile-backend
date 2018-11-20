package com.appzoneltd.lastmile.microservice.ums.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ums.entity.UserHubEntity;

/**
 * Repository : UserHub.
 */
public interface UserHubRepository extends PagingAndSortingRepository<UserHubEntity, Long> {

}
