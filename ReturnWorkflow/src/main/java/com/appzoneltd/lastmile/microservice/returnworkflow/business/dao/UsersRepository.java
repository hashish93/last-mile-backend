package com.appzoneltd.lastmile.microservice.returnworkflow.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.UsersEntity;

/**
 * Repository : Users.
 */
public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

	@Query(value = "SELECT user_id FROM lastmile_authorization_server.users WHERE phone=:phone", nativeQuery = true)
	Long getCustomerIdForPhoneNumber(@Param("phone") String phone);
	
}
