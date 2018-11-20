package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.CustomerEntity;
import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PackageEntity;

/**
 * Repository : Customer.
 */
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

	@Query("SELECT c FROM CustomerEntity c ORDER BY c.updated DESC")
	List<CustomerEntity> getOrderedCustomerEntites();
	
	@Query("SELECT c FROM CustomerEntity c WHERE c.updated > :updatedTime ORDER BY c.updated DESC")
	List<CustomerEntity> getlastUpdatedCustomersFrom(@Param("updatedTime") Date updatedTime);
}
