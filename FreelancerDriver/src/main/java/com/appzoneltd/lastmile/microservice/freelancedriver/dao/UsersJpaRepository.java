package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.UsersEntity;

/**
 * Repository : Users.
 */
public interface UsersJpaRepository extends PagingAndSortingRepository<UsersEntity, Long> {

	UsersEntity findByEmailOrPhone(String email, String phone);

	List<UsersEntity> findAllByPhone(String phone);

	List<UsersEntity> findAllByEmail(String email);
	
	
}
