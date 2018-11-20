package com.appzoneltd.lastmile.microservice.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.UsersRolesRepository;
import com.appzoneltd.lastmile.microservice.employee.entity.UserRoleEntity;

@Service
public class UserRoleService {

	@Autowired
	private UsersRolesRepository usersRolesRepository;
	
	public UserRoleEntity saveOrUpdate(UserRoleEntity userRoleEntity){
		return usersRolesRepository.save(userRoleEntity);
	}
	
	public void deleteBulk(List<UserRoleEntity> userRoleEntities){
		for(UserRoleEntity userRoleEntity : userRoleEntities){
			usersRolesRepository.delete(userRoleEntity);
		}
	}
	
}
