package com.appzoneltd.lastmile.microservice.ums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.ums.dao.RolePrivilegeRepository;
import com.appzoneltd.lastmile.microservice.ums.entity.RolePrivilegeEntity;

@Service
public class RolePrivilegeService {

	@Autowired
	private RolePrivilegeRepository rolePrivilegeRepository;
	
	@Transactional
	public RolePrivilegeEntity save(RolePrivilegeEntity rolePrivilegeEntity){
		return rolePrivilegeRepository.save(rolePrivilegeEntity);
	}
	
	@Transactional
	public void deleteBulk(List<RolePrivilegeEntity> rolePrivilegeEntities){
			rolePrivilegeRepository.delete(rolePrivilegeEntities);
	}
	
}
