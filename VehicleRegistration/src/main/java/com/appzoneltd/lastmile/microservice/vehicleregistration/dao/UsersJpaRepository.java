package com.appzoneltd.lastmile.microservice.vehicleregistration.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alaa.nabil
 *
 */
@Repository
public interface UsersJpaRepository extends CrudRepository<UsersEntity, Long> {
	UsersEntity findOneByEmailOrPhone(String email, String phone);
}
