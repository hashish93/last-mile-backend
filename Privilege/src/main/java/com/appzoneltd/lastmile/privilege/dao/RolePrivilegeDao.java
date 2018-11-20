package com.appzoneltd.lastmile.privilege.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.privilege.model.RolePrivilege;;



@Transactional
public interface RolePrivilegeDao extends CrudRepository<RolePrivilege, Long> {

  @Query("SELECT rp FROM RolePrivilege rp inner join rp.modulePrivilege mp inner join rp.role r WHERE r.id = :roleId AND mp.id = :modulePrivilegeId")	
  public RolePrivilege findForRoleAndModule(@Param("roleId") Integer roleId,@Param("modulePrivilegeId") Integer modulePrivilegeId);
	

}