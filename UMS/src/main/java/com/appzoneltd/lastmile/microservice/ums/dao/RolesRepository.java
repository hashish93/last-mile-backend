package com.appzoneltd.lastmile.microservice.ums.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ums.entity.RoleEntity;

/**
 * Repository : Roles.
 */
public interface RolesRepository extends PagingAndSortingRepository<RoleEntity, Long> {

	@Query("SELECT DISTINCT role FROM RoleEntity role inner join role.building building WHERE building.buildingId = :hubId ORDER BY role.id ASC")
	public List<RoleEntity> findAllHubRoles(@Param("hubId") Long hubId);
	
	@Query("SELECT DISTINCT role FROM RoleEntity role where role.building IS NULL AND role.systemRole = FALSE AND role.enabled= TRUE ORDER BY role.id ASC")
	public List<RoleEntity> findAllSharedRoles();
	
	@Query("SELECT DISTINCT role FROM RoleEntity role WHERE role.systemRole = FALSE ORDER BY role.id ASC")
	public List<RoleEntity> findAllRoles();
	
	@Query("SELECT role FROM RoleEntity role where role.roleId = :roleId")
	public RoleEntity findRoleById(@Param("roleId") Long roleId);
	
	@Query("SELECT DISTINCT role FROM RoleEntity role where LOWER(role.rolename) = LOWER(:rolename)")
	public RoleEntity findByRolename(@Param("rolename") String rolename);
	
	@Query("SELECT role FROM RoleEntity role inner join role.building b where b.buildingId = :hubId And role.enabled = true And role.editable =true AND role.systemRole = FALSE ORDER BY role.id ASC")
	public List<RoleEntity> findAllActiveRoles(@Param("hubId") Long hubId);
	
}
