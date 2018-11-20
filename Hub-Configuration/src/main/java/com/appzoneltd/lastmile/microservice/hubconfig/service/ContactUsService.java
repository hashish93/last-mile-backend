package com.appzoneltd.lastmile.microservice.hubconfig.service;

import com.appzoneltd.lastmile.microservice.hubconfig.dto.ContactusDto;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.ContactUsEntity;

public interface ContactUsService {

	ContactusDto findContactUsByLanguage(String lang);

	ContactUsEntity saveContactUs(ContactusDto contactUsDto);
	
	ContactUsEntity updateContactUs(ContactusDto contactusDto);


}
