package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CarsBrandsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : CarsBrands.
 */
public interface CarsBrandsJpaRepository extends PagingAndSortingRepository<CarsBrandsEntity, Long> {

}
