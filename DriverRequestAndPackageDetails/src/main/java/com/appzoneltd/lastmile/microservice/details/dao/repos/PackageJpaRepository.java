package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageEntity;

/**
 * Repository : Package.
 */
public interface PackageJpaRepository extends PagingAndSortingRepository<PackageEntity, Long> {

	PackageEntity findBypackageId(Long packageId);

//	@Modifying(clearAutomatically = true)
//	@Query(value = "UPDATE PackageEntity p SET p.packageType=:packageType, p.actualweight=:actualWeight, p.description=:description WHERE p.packageId=:id")
//	int updateDetails(@Param("packageType") PackageTypeEntity packageType, @Param("actualWeight") BigDecimal actualWeight,
//			@Param("description") String description, @Param("id") Long id);
}
