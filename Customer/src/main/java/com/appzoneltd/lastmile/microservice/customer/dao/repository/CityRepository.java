package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CityEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : City.
 */
public interface CityRepository extends PagingAndSortingRepository<CityEntity, Long> {

}
