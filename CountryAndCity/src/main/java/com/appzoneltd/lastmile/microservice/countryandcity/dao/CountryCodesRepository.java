package com.appzoneltd.lastmile.microservice.countryandcity.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository : CountryCodes.
 */
public interface CountryCodesRepository extends CrudRepository<CountryCodesEntity, Long> {
    List<CountryCodesEntity> findAllByOrderByNicenameAsc();
}
