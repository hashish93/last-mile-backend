package com.appzoneltd.lastmile.microservice.customer.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.customer.dao.CustomerFirebaseToken;
import com.appzoneltd.lastmile.microservice.customer.model.FirebaseTokenObject;
import com.appzoneltd.lastmile.microservice.customer.service.CustomerFirebaseTokenService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

/**
 * @author alaa.nabil
 */
@RestController
public class CustomerFirebaseTokenController {

	private final CustomerFirebaseTokenService customerFirebaseTokenService;

	@Autowired
	public CustomerFirebaseTokenController(CustomerFirebaseTokenService customerFirebaseTokenService) {
		super();
		this.customerFirebaseTokenService = customerFirebaseTokenService;
	}

	@RequestMapping(value = "/savefirebasetoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> createCustomer(@RequestBody FirebaseTokenObject firebaseInput) {
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		
		CustomerFirebaseToken result = 
				customerFirebaseTokenService.saveOrUpdateCustomerToken(firebaseInput,principal);
		
		if (result == null) {
			return new ResponseEntity<>(new Message(MessageType.ERROR, null), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
	}

	@RequestMapping(value = "/getcustomerfirebasetoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerFirebaseToken> updateCustomer(@RequestBody Map<String, Long> params) {
		CustomerFirebaseToken result = customerFirebaseTokenService.getCustomerToken(params.get("id"));
		return new ResponseEntity<CustomerFirebaseToken>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/revokeFirebasetoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> revokeFirebasetoken(@RequestBody Map<String, String> params) {

		try {
			Principal principal = SecurityContextHolder.getContext().getAuthentication();
			String tokenString = params.get("firebaseToken");
			customerFirebaseTokenService.removeToken(tokenString, principal);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

}
