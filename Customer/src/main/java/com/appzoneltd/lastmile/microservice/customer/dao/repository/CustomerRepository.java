package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Customer.
 */
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

}
