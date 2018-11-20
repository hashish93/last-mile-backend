package com.appzoneltd.lastmile.microservice.scheduledrequest.service;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.core.Message;
import com.appzoneltd.lastmile.core.Service;
import com.appzoneltd.lastmile.enums.MessageType;
import com.appzoneltd.lastmile.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.scheduledrequest.dao.ScheduledRequestDao;
import com.appzoneltd.lastmile.microservice.scheduledrequest.model.ScheduledRequest;

@RestController
public class ScheduledRequestCUDService extends Service {

	/**
	 * Inject DAO Class to Implemnt Transactional Function
	 */
	@Autowired
	ScheduledRequestDao scheduledRequestDao;

	/**
	 * Injecting MessageSource Class to perform Localization Messages .
	 * 
	 */
	@Autowired
	private MessageSource messageSource;
	/**
	 * Injecting Locale Class to get Current local .
	 * 
	 */
	@Autowired
	private Locale locale;

/*	@RequestMapping(value = "/editScheduledRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updateScheduledRequest(@RequestBody ScheduledRequest scheduledRequest)
			throws DuplicatedKeyException, ParseException {
		int result = 0;

		result = scheduledRequestDao.updateScheduledRequest(scheduledRequest);
		if (result == 0)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, Integer.toString(result), messageSource.getMessage(
							"scheduledRequest.update.error", null, "scheduledRequest.update.error", locale)),
					HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, Integer.toString(result), messageSource.getMessage(
						"scheduledRequest.update.success", null, "scheduledRequest.update.success", locale)),
				HttpStatus.OK);

	}*/

//	@PreAuthorize("hasRole('ROLE_deleteschedule')")
//	@RequestMapping(value = "/cancelScheduledRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Message> cancelScheduledRequest(@RequestBody ScheduledRequest scheduledRequest)
//			throws DuplicatedKeyException {
//		int result = 0;
//		result = scheduledRequestDao.cancelScheduledRequest(scheduledRequest);
//		if (result == 0)
//			return new ResponseEntity<Message>(
//					new Message(MessageType.ERROR, Integer.toString(result), messageSource.getMessage(
//							"scheduledRequest.cancel.error", null, "scheduledRequest.cancel.error", locale)),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//
//		return new ResponseEntity<Message>(
//				new Message(MessageType.SUCCESS, Integer.toString(result), messageSource.getMessage(
//						"scheduledRequest.cancel.success", null, "scheduledRequest.cancel.success", locale)),
//				HttpStatus.OK);
//
//	}

}
