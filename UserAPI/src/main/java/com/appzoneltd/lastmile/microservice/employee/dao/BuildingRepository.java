package com.appzoneltd.lastmile.microservice.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.employee.entity.BuildingEntity;

/**
 * Repository : Building.
 */
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, Long> {

	@Query("SELECT DISTINCT b FROM BuildingEntity b where b.buildingId =:hubId AND b.status = 'ACTIVE'")
	public BuildingEntity getBuildingById(@Param("hubId")Long hubId);
	
	@Query("SELECT b FROM BuildingEntity b where b.status = 'ACTIVE'")
	public List<BuildingEntity> getAllBuilding();
	
	@Query("SELECT DISTINCT b FROM BuildingEntity b inner join b.listOfUserHub uh inner join uh.user u inner join u.userType ut where b.status = 'ACTIVE' and ut.name <> 'HUB_ADMIN'")
	public List<BuildingEntity> findAllUnAssignedBuildings();
	
}
