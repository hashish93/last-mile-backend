package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CountryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Country.
 */
public interface CountryRepository extends PagingAndSortingRepository<CountryEntity, Long> {

}
