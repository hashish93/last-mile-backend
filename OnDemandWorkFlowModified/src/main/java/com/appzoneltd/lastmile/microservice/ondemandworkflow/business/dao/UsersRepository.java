package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.UserEntity;

/**
 * Repository : Users.
 */
public interface UsersRepository extends CrudRepository<UserEntity, Long> {

	@Query(value = "SELECT user_id FROM lastmile_authorization_server.users WHERE phone=:phone", nativeQuery = true)
	Long getCustomerIdForPhoneNumber(@Param("phone") String phone);
	
}
