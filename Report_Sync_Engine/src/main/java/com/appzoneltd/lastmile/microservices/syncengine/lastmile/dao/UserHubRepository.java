package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.UserHubEntity;

/**
 * Repository : UserHub.
 */
public interface UserHubRepository extends PagingAndSortingRepository<UserHubEntity, Long> {

}
