package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.CarsBrandsEntity;

/**
 * Repository : CarsBrands.
 */
public interface CarsBrandsJpaRepository extends PagingAndSortingRepository<CarsBrandsEntity, Long> {

}
