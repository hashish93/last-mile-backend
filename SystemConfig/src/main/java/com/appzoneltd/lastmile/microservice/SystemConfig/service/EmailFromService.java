package com.appzoneltd.lastmile.microservice.SystemConfig.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.SystemConfig.dao.EmailFromEntity;

public interface EmailFromService {

	EmailFromEntity saveEmailFrom(EmailFromEntity entity);

	void deleteEmailFrom(Integer id);

	List<EmailFromEntity> findAllEmailFrom();

	EmailFromEntity findEmailFromById(Integer id);

}
