package com.appzoneltd.lastmile.microservice.hubconfig.controller;

import java.util.List;

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
import com.appzoneltd.lastmile.microservice.hubconfig.entity.EmailFromEntity;
import com.appzoneltd.lastmile.microservice.hubconfig.service.EmailFromService;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
public class EmailFromController {

	@Autowired
	private EmailFromService emailFromService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/findallEmailFrom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmailFromEntity>> viewAllEmailFrom() {
		List<EmailFromEntity> entites = null;
		entites = emailFromService.findAllEmailFrom();
		if (entites == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(entites, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveEmailFrom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveEmailFrom(@RequestBody EmailFromEntity emailFromEntity) {

		EmailFromEntity entity = emailFromService.saveEmailFrom(emailFromEntity);
		if (entity == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "SUCCESS", messageSource.getMessage("contactus.update.success",
							null, "contactus.update.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
	}

	@RequestMapping(value = "/findEmailFromById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailFromEntity> findEmailFromById(@RequestBody EndPointParameter pointParameter) {
		Integer id = Integer.valueOf(pointParameter.getId().intValue());
		EmailFromEntity entity = null;
		entity = emailFromService.findEmailFromById(id);
		if (entity == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteEmailFromById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEmailFrom(@RequestBody EndPointParameter endPointParameter) {

		Integer id = Integer.valueOf(endPointParameter.getId().intValue());
		emailFromService.deleteEmailFrom(id);
		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, "SUCCESS", messageSource.getMessage("contactus.delete.success", null,
						"contactus.delete.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);

	}

}
