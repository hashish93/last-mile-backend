package com.appzoneltd.lastmile.microservice.employee.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.UserTypeRepository;
import com.appzoneltd.lastmile.microservice.employee.dto.UserTypeDto;
import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;
import com.appzoneltd.lastmile.microservice.employee.transformer.UserTypeTransformer;

@Service
public class UserTypeService {

	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private PrincipalService principalService;
	
	public List<UserTypeDto> getUserTypeDtos(Principal principal){
		List<UserTypeEntity> userTypeEntities = new ArrayList<>();
		if(principalService.isSuperAdmin(principal)){
			userTypeEntities = getAdminUserTypes();
		}else{
			userTypeEntities = getNormalUserTypes();
		}
		return UserTypeTransformer.getUserTypeDtosFromUserTypesEntities(userTypeEntities);
	}
	
	private List<UserTypeEntity> getAdminUserTypes(){
		return userTypeRepository.getAdminUserTypes();
	}
	
	private List<UserTypeEntity> getNormalUserTypes(){
		return userTypeRepository.getNormalUserTypes();
	}
	
	public UserTypeEntity getUserTypeEntity(Long userTypeId){
		return userTypeRepository.findOne(userTypeId);
	}
}
