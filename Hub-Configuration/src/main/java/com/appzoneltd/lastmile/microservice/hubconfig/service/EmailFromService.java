package com.appzoneltd.lastmile.microservice.hubconfig.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.hubconfig.entity.EmailFromEntity;

public interface EmailFromService {

	EmailFromEntity saveEmailFrom(EmailFromEntity entity);

	void deleteEmailFrom(Integer id);

	List<EmailFromEntity> findAllEmailFrom();

	EmailFromEntity findEmailFromById(Integer id);

}
