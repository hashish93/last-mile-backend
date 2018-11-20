package com.appzoneltd.lastmile.microservice.delivery.dao.repository;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Customer.
 */
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

}
