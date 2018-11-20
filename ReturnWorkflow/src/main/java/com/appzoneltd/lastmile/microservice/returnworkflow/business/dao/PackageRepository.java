package com.appzoneltd.lastmile.microservice.returnworkflow.business.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.PackageEntity;

/**
 * Repository : Package.
 */
public interface PackageRepository extends CrudRepository<PackageEntity, Long> {

}
