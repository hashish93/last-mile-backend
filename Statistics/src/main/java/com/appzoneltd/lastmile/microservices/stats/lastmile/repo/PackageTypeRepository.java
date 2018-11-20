package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.PackageTypeEntity;

/**
 * Repository : PackageType.
 */
public interface PackageTypeRepository extends CrudRepository<PackageTypeEntity, Long> {
	
	@Query(value="SELECT package_type_id FROM lastmile_core.package_type",nativeQuery=true)
	List<Long> findAllPackageTypeIds();

}
