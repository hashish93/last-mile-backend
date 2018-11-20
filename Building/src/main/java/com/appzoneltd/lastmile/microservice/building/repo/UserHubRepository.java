package com.appzoneltd.lastmile.microservice.building.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.UserHubEntity;

/**
 * Repository : UserHub.
 */
public interface UserHubRepository extends PagingAndSortingRepository<UserHubEntity, Long> {

}
