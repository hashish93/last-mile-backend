package com.appzoneltd.lastmile.microservice.ums.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.ums.model.MyPrincipal;
import com.appzoneltd.lastmile.microservice.ums.model.MyUser;
import com.appzoneltd.lastmile.microservice.ums.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MyUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping("/user")
	public MyUser user(Principal principal) throws JsonParseException, JsonMappingException, IOException {

		MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(), MyPrincipal.class);
		UserEntity userEntity = userService.getUserById(myPrincipal.getUserId());

		MyUser myUser = new MyUser();
		if (userEntity != null) {
			myUser.setUserId(userEntity.getUserId());
			myUser.setUsername(userEntity.getUsername());
			myUser.setEmail(userEntity.getEmail());
			myUser.setPhone(userEntity.getPhone());
			myUser.setStatus(userEntity.getStatus());
			myUser.setDescription(userEntity.getDescription());
			if (userEntity.getUserType() != null) {
				myUser.setUserType(userEntity.getUserType().getName());
				myUser.setUserTypeId(userEntity.getUserType().getId());
			}
			myUser.setPersonalPhotoId(userEntity.getPhotoId());
			myUser.setPageSize(25);
		}
		return myUser;
	}

}
