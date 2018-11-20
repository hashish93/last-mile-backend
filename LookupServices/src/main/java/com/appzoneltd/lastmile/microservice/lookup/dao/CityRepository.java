package com.appzoneltd.lastmile.microservice.lookup.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.lookup.entity.CityEntity;

/**
 * Repository : City.
 */
public interface CityRepository extends PagingAndSortingRepository<CityEntity, Long> {

	@Query("SELECT c FROM CityEntity c inner join c.country cc where cc.countryId = :countryId ORDER BY c.nameEn ASC")
	public List<CityEntity> getCitiesByCountryId(@Param("countryId") Long countryId);

	public List<CityEntity> findAllByOrderByNameEn();

}
