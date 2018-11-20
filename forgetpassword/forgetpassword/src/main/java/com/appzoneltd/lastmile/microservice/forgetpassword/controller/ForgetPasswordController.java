package com.appzoneltd.lastmile.microservice.forgetpassword.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.forgetpassword.model.RequestParameter;
import com.appzoneltd.lastmile.microservice.forgetpassword.model.ValidationUrl;
import com.appzoneltd.lastmile.microservice.forgetpassword.service.ForgetPassword;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ForgetPasswordController {

	private final ForgetPassword forgetPassword;

	public ForgetPasswordController(ForgetPassword forgetPassword) {
		this.forgetPassword = forgetPassword;
	}

	@RequestMapping(value = "/forget", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> forgetPassword(@RequestBody RequestParameter requestParameter)
			throws JsonProcessingException {
		boolean chkUserEmailExist = forgetPassword.resetNewPassword(requestParameter.getEmail());
		return new ResponseEntity<>(chkUserEmailExist, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkGeneratedCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkGeneratedCode(@RequestBody RequestParameter requestParameter) {
		ValidationUrl checkGeneratedCode = forgetPassword.validateGeneratedCode(requestParameter.getGeneratedCode());
		return new ResponseEntity<>(checkGeneratedCode, HttpStatus.OK);
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> resetNewPassword(@RequestBody RequestParameter requestParameter) {
		boolean result = forgetPassword.resetNewPassword(requestParameter.getNewPassword(),
				requestParameter.getRepeatedPassword(), requestParameter.getEmail());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
