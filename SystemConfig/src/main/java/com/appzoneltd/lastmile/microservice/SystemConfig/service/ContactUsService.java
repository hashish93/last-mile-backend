package com.appzoneltd.lastmile.microservice.SystemConfig.service;

import com.appzoneltd.lastmile.microservice.SystemConfig.dao.ContactUsEntity;
import com.appzoneltd.lastmile.microservice.SystemConfig.dto.ContactusDto;

public interface ContactUsService {


	
	ContactusDto findContactUsByLanguage(String lang);

	ContactUsEntity saveContactUs(ContactusDto contactUsDto);
	
	ContactUsEntity updateContactUs(ContactusDto contactusDto);



}
