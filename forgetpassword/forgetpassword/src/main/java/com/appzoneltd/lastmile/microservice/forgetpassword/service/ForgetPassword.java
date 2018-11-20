package com.appzoneltd.lastmile.microservice.forgetpassword.service;

import com.appzoneltd.lastmile.microservice.forgetpassword.model.ValidationUrl;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ForgetPassword {
	
	Boolean resetNewPassword (String email) throws JsonProcessingException ;
	ValidationUrl validateGeneratedCode (String genratedCode);
	Boolean resetNewPassword (String newPassword , String repeatedPassword ,String email);
	
	
	

}
