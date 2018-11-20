package com.appzoneltd.lastmile.microservice.hubconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.hubconfig.dto.ContactusDto;
import com.appzoneltd.lastmile.microservice.hubconfig.dto.EndPointParam;
import com.appzoneltd.lastmile.microservice.hubconfig.entity.ContactUsEntity;
import com.appzoneltd.lastmile.microservice.hubconfig.service.ContactUsService;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
public class ContactUsController {

	private final ContactUsService contactUsService;
	private final MessageSource messageSource;

	@Autowired
	public ContactUsController(ContactUsService contactUsService, MessageSource messageSource) {
		this.contactUsService = contactUsService;
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/findContactUsByLang", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContactusDto> findConatctUsByLanguage(@RequestBody EndPointParam endPointParam) {

		ContactusDto entity = null;
		entity = contactUsService.findContactUsByLanguage(endPointParam.getLang());
		if (entity == null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveContactUs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveConatctUsentity(@RequestBody ContactusDto contactusDto) {

		ContactUsEntity entity = contactUsService.saveContactUs(contactusDto);
		if (entity == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "SUCCESS", messageSource.getMessage("contactus.update.success",
							null, "contactus.update.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
	}

}
