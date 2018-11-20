package com.appzoneltd.lastmile.microservice.SystemConfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.SystemConfig.dao.EmailFromEntity;
import com.appzoneltd.lastmile.microservice.SystemConfig.dao.EmailFromJpaRepository;

@Service
public class EmailFromServiceImp implements EmailFromService {

	private final EmailFromJpaRepository emailFromJpaRepository;

	@Autowired
	public EmailFromServiceImp(EmailFromJpaRepository emailFromJpaRepository) {
		this.emailFromJpaRepository = emailFromJpaRepository;
	}

	@Override
	public EmailFromEntity saveEmailFrom(EmailFromEntity emailFromEntity) {

		EmailFromEntity entity = emailFromJpaRepository.save(emailFromEntity);
		return entity;
	}

	@Override
	public void deleteEmailFrom(Integer id) {
		emailFromJpaRepository.delete(id);

	}

	@Override
	public List<EmailFromEntity> findAllEmailFrom() {
		List<EmailFromEntity> entites = null;
		entites = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
		return entites;
	}

	@Override
	public EmailFromEntity findEmailFromById(Integer id) {
		EmailFromEntity entity = null;
		entity = emailFromJpaRepository.findOne(id);
		return entity;
	}

}
