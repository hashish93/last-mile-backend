package com.appzoneltd.lastmile.microservice.customer.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.ContactUsEntity;

public interface ContactUsService {


	List<ContactUsEntity> listContactUsByLanguage(String lang);
}
