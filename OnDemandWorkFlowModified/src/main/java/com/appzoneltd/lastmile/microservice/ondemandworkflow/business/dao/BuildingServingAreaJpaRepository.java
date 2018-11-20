package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.BuildingServingAreaEntity;

/**
 * Repository : BuildingServingArea.
 */
@Repository
public interface BuildingServingAreaJpaRepository extends CrudRepository<BuildingServingAreaEntity, Long> {

	@Query(value = "SELECT area from BuildingServingAreaEntity area inner join area.building b WHERE b.status = 'ACTIVE'")
	public List<BuildingServingAreaEntity> findAllActiveServingAreas();

}
