package com.appzoneltd.lastmile.privilege.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.privilege.model.ModulePrivilege;;



@Transactional
public interface ModulePrivilegeDao extends CrudRepository<ModulePrivilege, Long> {
	
	@Query("SELECT mp FROM ModulePrivilege mp WHERE mp.id = :modulePrivilegeId")
	public ModulePrivilege findById(@Param("modulePrivilegeId") Integer modulePrivilegeId);
	
	@Query("SELECT mp FROM ModulePrivilege mp inner join mp.module m ORDER BY m.id ASC")
	public List<ModulePrivilege> findAll();
	
}