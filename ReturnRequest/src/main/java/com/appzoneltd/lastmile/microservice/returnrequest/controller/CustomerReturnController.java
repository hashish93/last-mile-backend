package com.appzoneltd.lastmile.microservice.returnrequest.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.CustomerRequestDetails;
import com.appzoneltd.lastmile.microservice.returnrequest.service.CustomerRequestDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alaa.nabil on 5/10/2017.
 */
@RestController
public class CustomerReturnController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerReturnController.class);

	private final MessageSource messageSource;
	private final CustomerRequestDetailsService customerRequestDetailsService;

	@Autowired
	public CustomerReturnController(MessageSource messageSource, CustomerRequestDetailsService customerRequestDetailsService) {
		this.messageSource = messageSource;
		this.customerRequestDetailsService = customerRequestDetailsService;
	}

	@RequestMapping(value = "/customerRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerRequestDetails> customerRequestDetails(@RequestBody HashMap<String, Long> params) {
		CustomerRequestDetails customerRequestDetails = customerRequestDetailsService.getRequestDetailsForCustomer(params.get("id"));
		return new ResponseEntity<>(customerRequestDetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/isTrackedRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> checkRequestAvailability(@RequestBody Map<String, Long> requestparameters) {

		Boolean isTracked = customerRequestDetailsService.checkTrackedPickupRequest(requestparameters.get("id"));

		Message message = new Message(MessageType.ERROR, isTracked.toString());

		if (isTracked) {
			message.setMessageType(MessageType.SUCCESS);
		}

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
