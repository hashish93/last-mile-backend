package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CountryCodesEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : CountryCodes.
 */
public interface CountryCodesRepository extends PagingAndSortingRepository<CountryCodesEntity, Long> {

}
