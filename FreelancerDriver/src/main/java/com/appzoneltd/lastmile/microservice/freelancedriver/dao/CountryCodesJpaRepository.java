package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.CountryCodesEntity;

/**
 * Repository : CountryCodes.
 */
public interface CountryCodesJpaRepository extends PagingAndSortingRepository<CountryCodesEntity, Long> {

}
