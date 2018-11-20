package com.appzoneltd.lastmile.microservice.building.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.CountryCodesEntity;

/**
 * Repository : CountryCodes.
 */
public interface CountryCodesRepository extends PagingAndSortingRepository<CountryCodesEntity, Long> {

}
