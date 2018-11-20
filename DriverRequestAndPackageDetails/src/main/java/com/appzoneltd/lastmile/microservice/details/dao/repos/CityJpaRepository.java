package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CityEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : City.
 */
public interface CityJpaRepository extends PagingAndSortingRepository<CityEntity, Long> {

}
