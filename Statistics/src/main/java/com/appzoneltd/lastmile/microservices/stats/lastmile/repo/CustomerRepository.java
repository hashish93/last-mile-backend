package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.CustomerEntity;

/**
 * Repository : Customer.
 */
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

	@Query("SELECT COUNT(c) FROM CustomerEntity c WHERE c.gender=:gender ")
	public Long getMaleCustomers(@Param("gender") String gender);

}
