package com.appzoneltd.lastmile.microservice.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.RolesRepository;
import com.appzoneltd.lastmile.microservice.employee.entity.RoleEntity;


@Service
@Qualifier("roleService")
public class RoleService {

	@Autowired
	private RolesRepository rolesRepository;
	
	public RoleEntity getRoleById(Long roleId) {
		return rolesRepository.findOne(roleId);
	}

	public RoleEntity getRoleByName(String roleName) {
		return rolesRepository.findByRolename(roleName);
	}
	
	
}
