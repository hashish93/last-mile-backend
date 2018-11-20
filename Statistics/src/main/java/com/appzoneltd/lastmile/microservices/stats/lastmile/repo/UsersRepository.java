package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.UsersEntity;

/**
 * Repository : Users.
 */
public interface UsersRepository extends PagingAndSortingRepository<UsersEntity, Long> {

}
