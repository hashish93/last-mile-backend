package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.CountryCodesEntity;

/**
 * Repository : CountryCodes.
 */
public interface CountryCodesRepository extends PagingAndSortingRepository<CountryCodesEntity, Long> {

}
