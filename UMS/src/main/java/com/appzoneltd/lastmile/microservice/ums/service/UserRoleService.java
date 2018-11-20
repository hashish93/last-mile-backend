package com.appzoneltd.lastmile.microservice.ums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.ums.dao.UsersRolesRepository;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntity;

@Service
public class UserRoleService {

	@Autowired
	private UsersRolesRepository usersRolesRepository;
	
	@Transactional
	public UserRoleEntity saveOrUpdate(UserRoleEntity userRoleEntity){
		return usersRolesRepository.save(userRoleEntity);
	}
	
	@Transactional
	public void deleteBulk(List<UserRoleEntity> userRoleEntities){
		usersRolesRepository.delete(userRoleEntities);
	}
	
}
