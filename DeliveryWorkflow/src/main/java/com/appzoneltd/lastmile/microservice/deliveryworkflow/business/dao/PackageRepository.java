package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.PackageEntity;

/**
 * Repository : Package.
 */
public interface PackageRepository extends CrudRepository<PackageEntity, Long> {

}
