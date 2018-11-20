package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.details.service.exception.InvalidNewPasswordException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.Principal;

public interface DriverService {

	DriverProfileDTO getDriverProfileByPrincipal(Principal principal) throws EntityNotFoundException;

	Boolean forgotPassword(UnverifiedNumber unverifiedNumber) throws EntityNotFoundException, JsonProcessingException;

	boolean forgotPasswordVerifyToken(ForgetPasswordVerifyToken forgetPasswordVerifyToken)
			throws EntityNotFoundException, ExceededNumberOfAttempts, ForgotPasswordTokenExpired;

	boolean changeCurrentPassword(Principal principal, NewPassword newPassword) throws EntityNotFoundException;
	
	void updateProfileImageByPrincipal(Principal principal, Long newImageId) throws EntityNotFoundException;

	Object verifyDriverPhone(Principal principal, String phone) throws EntityNotFoundException, JsonProcessingException;

	boolean updateDriverPhone(Principal principal, String phone, String verificationCode) throws EntityNotFoundException ;

	boolean handleEmailChange(ChangeEmailModel changeEmailModel, Principal principal) throws EntityNotFoundException, JsonProcessingException;

	Boolean verifyChangedEmail(String cipher, Principal authentication);

	void setNewPassworsd(String phone, String newPass)throws EntityNotFoundException, InvalidNewPasswordException;

}
