package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PackageTypeSummaryEntity;

/**
 * Repository : PackageTypeSummary.
 */
public interface PackageTypeSummaryRepository extends CrudRepository<PackageTypeSummaryEntity, Long> {

}
