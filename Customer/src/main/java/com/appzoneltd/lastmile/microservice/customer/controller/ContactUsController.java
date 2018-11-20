package com.appzoneltd.lastmile.microservice.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.ContactUsEntity;
import com.appzoneltd.lastmile.microservice.customer.service.ContactUsService;

/**
 * @author ibrahim.ali
 */

@RestController
public class ContactUsController {

	@Autowired
	private ContactUsService conactUsService;
	
    @RequestMapping(value = "/contactus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContactUs() {
    	
    	String chosenLanguage = LocaleContextHolder.getLocale().getLanguage();
    	
    	List<ContactUsEntity> outList =  this.conactUsService.listContactUsByLanguage(chosenLanguage);
    	
    	return new ResponseEntity<>(outList, HttpStatus.OK);
    }
}
