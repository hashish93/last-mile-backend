package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.UsersEntity;

/**
 * Repository : Users.
 */
public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

	@Query(value = "SELECT user_id FROM lastmile_authorization_server.users u "
			+ "inner join lastmile_core.customer c on u.user_id = c.customer_id "
			+ "WHERE phone=:phone", nativeQuery = true)
	Long getCustomerIdForPhoneNumber(@Param("phone") String phone);
	
}
