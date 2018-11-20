package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingRepository extends CrudRepository<BuildingEntity, Long> {

}
