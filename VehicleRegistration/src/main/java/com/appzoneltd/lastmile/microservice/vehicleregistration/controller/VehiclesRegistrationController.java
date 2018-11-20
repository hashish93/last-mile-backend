package com.appzoneltd.lastmile.microservice.vehicleregistration.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.RegistrationModel;
import com.appzoneltd.lastmile.microservice.vehicleregistration.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @author alaa.nabil
 */
@RestController
public class VehiclesRegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(VehiclesRegistrationController.class);

	@Autowired
	private RegistrationService registrationService;

	/**
	 * @param registerationModel
	 * @return
	 */
	@RequestMapping(value = "/registervehicle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerVehicle(@RequestBody RegistrationModel registerationModel) {
		ResponseEntity<Object> responseEntity;
		try {
			RegistrationModel model = registrationService.saveOrUpdate(registerationModel);
			if (model == null)
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			else
				responseEntity = new ResponseEntity<Object>(model, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

    @RequestMapping(value = "/savefirebasetoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateToken(@RequestBody Map<String, String> params) {
    	Principal principal = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Object> responseEntity;
        try {
            RegistrationModel model = registrationService.updateToken(params.get("firebaseToken"),
            		principal);
            if (model == null)
                responseEntity = new ResponseEntity<>(new Message(MessageType.ERROR, null), HttpStatus.BAD_REQUEST);
            else
                responseEntity = new ResponseEntity<Object>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/revokeFirebasetoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> revokeFirebasetoken(@RequestBody Map<String, String> params) {
    	Principal principal = SecurityContextHolder.getContext().getAuthentication();
    	final RegistrationModel firebaseToken = registrationService.removeToken(params.get("firebaseToken"), principal);
        return ResponseEntity.ok(firebaseToken);
    }
}
