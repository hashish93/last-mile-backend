package com.appzoneltd.lastmile.microservice.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.ContactUsEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.ContactUsRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	
	private ContactUsRepository contactUsRepo;
	
	@Autowired
	public ContactUsServiceImpl(ContactUsRepository repo) {
		this.contactUsRepo = repo;
	}
	@Override
	public List<ContactUsEntity> listContactUsByLanguage(String lang) {
		if (lang == null || lang.trim().isEmpty()) {
			lang = "ar";
		}
		return this.contactUsRepo.findByLanguage(lang);
	}

}
