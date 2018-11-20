package com.appzoneltd.lastmile.microservice.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.UserHubRepository;
import com.appzoneltd.lastmile.microservice.employee.entity.UserHubEntity;


@Service
public class UserHubService {

	@Autowired
	private UserHubRepository userHubRepository;
	
	public boolean isHubAdminExistsForHub(Long hubId){
		List<UserHubEntity> userHubEntities = userHubRepository.getUsersForHub(hubId);
		if(userHubEntities != null &&  userHubEntities.size()>0)
			return true;
		return false;
	}
	
	public boolean isUserRelatedToHub(Long userId , Long hubId){
		UserHubEntity userHubEntity = userHubRepository.getUserHubForUserAndHub(userId , hubId);
		if(userHubEntity != null)
			return true;
		return false;
	}
}
