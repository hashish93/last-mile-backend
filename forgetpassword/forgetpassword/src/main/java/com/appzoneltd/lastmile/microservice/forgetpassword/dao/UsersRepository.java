package com.appzoneltd.lastmile.microservice.forgetpassword.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.forgetpassword.entity.UsersEntity;

/**
 * Repository : Users.
 */
public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

	public UsersEntity findByEmail(String email);

}
