package com.appzoneltd.lastmile.microservice.details.controller.freelancerdriver;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.details.dao.entity.FreelancerDriverEntity;
import com.appzoneltd.lastmile.microservice.details.dto.ActivateAccountModel;
import com.appzoneltd.lastmile.microservice.details.dto.FreelancerDriver;
import com.appzoneltd.lastmile.microservice.details.service.FreelanceDriverService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@ConditionalOnProperty(value = "features.FREELANCER_DRIVER.enabled", havingValue = "true")
public class FreelancerDriverRestController {

	@Autowired
	private FreelanceDriverService freelanceDriverService;

	@Autowired
	private MessageSource messageSource;


	/**
	 * register freelance driver
	 * @param freelancerDriver
	 * @return
	 * @throws DuplicatedKeyException
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCustomer(@Validated @RequestBody FreelancerDriver freelancerDriver)
			throws DuplicatedKeyException, JsonProcessingException {

		Object result = freelanceDriverService.saveFreelancerDriver(freelancerDriver);
		if (result instanceof FreelancerDriverEntity) {
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "created", messageSource.getMessage("freelancer.create.success",
							null, "freelancer.create.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

	/**
	 * freelance driver submit verification code generated and sent in SMS to this service,  
	 * @param tokenVerification
	 * @return
	 * @throws DuplicatedKeyException
	 * @throws JsonProcessingException
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/submitRegisterVerification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> activateCustomer(@Validated @RequestBody ActivateAccountModel tokenVerification)
			throws DuplicatedKeyException, JsonProcessingException, EntityNotFoundException {

		String authtoken;
		try {
			authtoken = freelanceDriverService.activateFreelancerDriver(tokenVerification);
			if (!authtoken.isEmpty()) {

				return new ResponseEntity<String>(authtoken,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}				
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/toggleFreelancerStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> toggleFreelancerOnOff(@RequestBody Map<String,Boolean> onOffStatus) {

		Principal driverPrincipal = SecurityContextHolder.getContext().getAuthentication();
		freelanceDriverService.toggleFreelancerOnOff(driverPrincipal,onOffStatus.get("status"));

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS,"success"),
				HttpStatus.OK);
	}
}
