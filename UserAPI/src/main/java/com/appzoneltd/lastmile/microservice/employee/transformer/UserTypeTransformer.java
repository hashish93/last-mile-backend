package com.appzoneltd.lastmile.microservice.employee.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.employee.dto.UserTypeDto;
import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;


public class UserTypeTransformer {

	public static List<UserTypeDto> getUserTypeDtosFromUserTypesEntities(List<UserTypeEntity> userTypeEntities) {
		List<UserTypeDto> userTypeDtos = new ArrayList<>();
		for (UserTypeEntity userTypeEntity : userTypeEntities) {
			userTypeDtos.add(getUserTypeDtoFromUserTypeEntity(userTypeEntity));
		}
		return userTypeDtos;
	}
	
	public static UserTypeDto getUserTypeDtoFromUserTypeEntity(UserTypeEntity userTypeEntity){
		UserTypeDto userTypeDto = new UserTypeDto();
		userTypeDto.setName(userTypeEntity.getName());
		userTypeDto.setValue(userTypeEntity.getId());
		return userTypeDto;
	}
}
