package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CountryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Country.
 */
public interface CountryJpaRepository extends PagingAndSortingRepository<CountryEntity, Long> {

}
