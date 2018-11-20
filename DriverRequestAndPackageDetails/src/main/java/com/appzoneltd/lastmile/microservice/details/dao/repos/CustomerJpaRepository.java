package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CustomerEntity;

/**
 * Repository : Customer.
 */
public interface CustomerJpaRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

}
