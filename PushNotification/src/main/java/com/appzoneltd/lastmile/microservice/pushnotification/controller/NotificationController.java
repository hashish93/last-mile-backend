package com.appzoneltd.lastmile.microservice.pushnotification.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.pushnotification.dao.NotificationModel;
import com.appzoneltd.lastmile.microservice.pushnotification.firebase.MulticastResult;
import com.appzoneltd.lastmile.microservice.pushnotification.service.NotificationService;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception.NotificationSendingException;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class NotificationController {

	private final NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> send(@RequestBody NotificationModel notificationModel) throws IOException {
		MulticastResult result = null;

		try {
			notificationService.sendNotification(notificationModel.getUserIds(), notificationModel.getData(),
					notificationModel.getRecipientType());
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (NotificationSendingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		

	}

}
