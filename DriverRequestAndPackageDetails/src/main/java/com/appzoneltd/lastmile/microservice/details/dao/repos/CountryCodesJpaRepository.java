package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CountryCodesEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : CountryCodes.
 */
public interface CountryCodesJpaRepository extends PagingAndSortingRepository<CountryCodesEntity, Long> {

}
