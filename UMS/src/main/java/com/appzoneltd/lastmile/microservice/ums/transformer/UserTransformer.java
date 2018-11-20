package com.appzoneltd.lastmile.microservice.ums.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.ums.dto.UserDto;
import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;

public class UserTransformer {

	public static List<UserDto> getUsersDtoFromUserEntities(List<UserEntity> userEntities) {
		List<UserDto> userDtos = new ArrayList<>();
		for (UserEntity userEntity : userEntities) {
			userDtos.add(getUserDtoFromUserEntity(userEntity));
		}
		return userDtos;
	}

	public static UserDto getUserDtoFromUserEntity(UserEntity userEntity) {

		UserDto userDto = new UserDto();
		userDto.setId(userEntity.getUserId());
		userDto.setFirstname(userEntity.getFirstname());
		userDto.setLastname(userEntity.getLastname());
		userDto.setUsername(userEntity.getUsername());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPhone(userEntity.getPhone());
		userDto.setUserType(userEntity.getUserType().getName());

		return userDto;
	}

}
