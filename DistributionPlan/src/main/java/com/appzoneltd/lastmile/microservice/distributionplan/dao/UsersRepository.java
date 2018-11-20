package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Users.
 */
public interface UsersRepository extends PagingAndSortingRepository<UsersEntity, Long> {
	
	UsersEntity findByUserId(Long userId);

}
