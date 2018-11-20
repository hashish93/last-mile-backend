package com.appzoneltd.lastmile.microservice.countryandcity.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alaa.nabil
 *
 */
@Repository
public interface CoveredCityJpaRepository extends PagingAndSortingRepository<CoveredCityEntity, Long> {

	List<CoveredCityEntity> findAllByOrderByNameEn();
}
