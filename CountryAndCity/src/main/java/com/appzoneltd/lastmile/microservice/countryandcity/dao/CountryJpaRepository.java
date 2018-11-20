package com.appzoneltd.lastmile.microservice.countryandcity.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @author alaa.nabil
 *
 */
@Repository
public interface CountryJpaRepository extends PagingAndSortingRepository<CountryEntity, Long> {
	List<CountryEntity> findByOrderByNameEnAsc();
}
