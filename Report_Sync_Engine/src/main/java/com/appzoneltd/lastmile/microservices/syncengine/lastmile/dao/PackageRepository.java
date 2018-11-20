package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PackageEntity;

/**
 * Repository : Package.
 */
public interface PackageRepository extends CrudRepository<PackageEntity, Long> {

	@Query("SELECT p FROM PackageEntity p ORDER BY p.updated DESC")
	List<PackageEntity> getOrderedPackageEntites();
	
	@Query("SELECT p FROM PackageEntity p WHERE p.updated > :updatedTime ORDER BY p.updated DESC")
	List<PackageEntity> getlastUpdatedPackagesFrom(@Param("updatedTime") Date updatedTime);
	
}
