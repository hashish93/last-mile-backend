package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PackageTypeEntity;

/**
 * Repository : PackageType.
 */
public interface PackageTypeRepository extends CrudRepository<PackageTypeEntity, Long> {

}
