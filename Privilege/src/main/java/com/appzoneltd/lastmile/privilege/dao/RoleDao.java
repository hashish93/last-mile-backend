package com.appzoneltd.lastmile.privilege.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.privilege.model.Module;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.model.RolePrivilege;



@Transactional
public interface RoleDao extends CrudRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.id!=0 ORDER BY r.id ASC")
	public List<Role> findAll();
	
	@Query("SELECT r FROM Role r WHERE r.enabled = true AND r.editable = false AND r.id !=0 ORDER BY r.id ASC")
	public List<Role> findAllActive();
	
	@Query("SELECT r FROM Role r WHERE r.editable = false ORDER BY r.id ASC")
	public List<Role> findAllExceptSuperUser();
	
	@Query("SELECT r FROM Role r inner join r.rolePrivileges rp inner join rp.modulePrivilege mp where r.id = :roleId AND mp IS NOT NULL AND r.editable = false")
	public Role findFullRole(@Param("roleId") Integer roleId);
	
	@Query("SELECT r FROM Role r where r.id = :roleId AND r.editable = false")
	public Role findById(@Param("roleId") Integer roleId);
	
	@Query("SELECT r FROM Role r inner join r.users u inner join r.rolePrivileges rp WHERE u.id = :userId AND r.enabled = true")
	public List<Role> findByUser(@Param("userId") Integer userId);
	
	
	@Query("SELECT r FROM Role r inner join r.users u WHERE (u.userName = :userIdentifier or u.email = :userIdentifier or u.phone = :userIdentifier) AND r.editable = false")
	public Role findByLoginData(@Param("userIdentifier") String userIdentifier);

	@Query("SELECT distinct m FROM Role r inner join r.rolePrivileges rp inner join rp.modulePrivilege mp inner join mp.module m")
	public List<Module> getRoleModules();
	
	@Query("SELECT rp FROM Role r inner join r.rolePrivileges rp inner join rp.modulePrivilege mp where mp.id = :modulePrivilegeId AND r.id = :roleId AND r.editable = false")
	public RolePrivilege getRolePrivilageForModulePrivilage(@Param("roleId") Integer roleId,@Param("modulePrivilegeId") Integer modulePrivilegeId);
	
	@Query("SELECT r FROM Role r WHERE r.name=:roleName")
	public Role chkExistingRoleName (@Param ("roleName") String name);
}