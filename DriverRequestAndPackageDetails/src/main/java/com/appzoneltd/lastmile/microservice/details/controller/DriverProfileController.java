package com.appzoneltd.lastmile.microservice.details.controller;

import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.details.service.DriverService;
import com.appzoneltd.lastmile.microservice.details.service.exception.InvalidNewPasswordException;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.security.Principal;
import java.util.Map;

@RestController
public class DriverProfileController {

    private DriverService driverService;
    private MessageSource messageSource;

    @Autowired
    public DriverProfileController(DriverService driverService, MessageSource messageSource) {
        this.driverService = driverService;
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forgotPassword(@RequestBody UnverifiedNumber unverifiedNumber)
            throws EntityNotFoundException, JsonProcessingException {
        if (driverService.forgotPassword(unverifiedNumber)) {
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/forgotPasswordVerifyToken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forgotPasswordVerifyToken(@RequestBody ForgetPasswordVerifyToken forgetPasswordVerifyToken)
            throws EntityNotFoundException, JsonProcessingException {
        try {
            if (!driverService.forgotPasswordVerifyToken(forgetPasswordVerifyToken)) {
                return new ResponseEntity<>(new Message(MessageType.ERROR, messageSource
                        .getMessage("invalid.forgot.password.token", null, LocaleContextHolder.getLocale())),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (ExceededNumberOfAttempts exceededNumberOfAttempts) {
            return new ResponseEntity<>(new Message(MessageType.ERROR, exceededNumberOfAttempts.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (ForgotPasswordTokenExpired forgotPasswordTokenExpired) {
            return new ResponseEntity<>(new Message(MessageType.ERROR, forgotPasswordTokenExpired.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/addNewPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setNetPassword(@RequestBody Map<String, String> paramsMap) {

        try {
            driverService.setNewPassworsd(paramsMap.get("phone"), paramsMap.get("password"));

            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);

        } catch (EntityNotFoundException | InvalidNewPasswordException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Message(MessageType.ERROR, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeCurrentPassword(@RequestBody NewPassword newPassword)
            throws EntityNotFoundException, JsonProcessingException {

        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        if (driverService.changeCurrentPassword(principal, newPassword)) {

            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/driverProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> driverProfile() throws EntityNotFoundException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal == null) {
            return new ResponseEntity<>(new Message(MessageType.ERROR, messageSource.getMessage("driver.not.exist",
                    null, "driver.not.exist", LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
        }
        DriverProfileDTO driverProfileDTO = driverService.getDriverProfileByPrincipal(principal);
        return new ResponseEntity<DriverProfileDTO>(driverProfileDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateProfileImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProfileImage(@RequestBody Map<String, Long> imageIdMap)
            throws EntityNotFoundException {

        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        if (principal == null) {
            return new ResponseEntity<>(new Message(MessageType.ERROR, messageSource.getMessage("driver.not.exist",
                    null, "driver.not.exist", LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
        }

        Long newImageId = imageIdMap.get("newImageId");

        if (newImageId != null) {
            driverService.updateProfileImageByPrincipal(principal, newImageId);
        }

        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/changeMobilenumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> verifyMobileNumber(@Validated @RequestBody DriverProfileDTO driverProfileDTO)
            throws EntityNotFoundException, JsonProcessingException {

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Object result = driverService.verifyDriverPhone(principal, driverProfileDTO.getPhoneNumber());

        if (result instanceof Map) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmMobileNumberChange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeMobileNumber(@RequestBody DriverProfileDTO driverProfileDTO)
            throws EntityNotFoundException, JsonProcessingException {

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        boolean result = driverService.updateDriverPhone(principal, driverProfileDTO.getPhoneNumber(),
                driverProfileDTO.getVerificationCode());
        if (result) {
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);

        }

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/changeEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeEmail(@Validated @RequestBody ChangeEmailModel changeEmailModel)
            throws EntityNotFoundException, JsonProcessingException {

        boolean result = driverService.handleEmailChange(changeEmailModel, SecurityContextHolder.getContext().getAuthentication());
        if (result) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("driver.update.success",
                            null, "driver.update.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @RequestMapping(value = "/confirmEmailChange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmEmailChange(@RequestBody Map<String, String> mapBody)
            throws EntityNotFoundException, JsonProcessingException {
        Boolean isVerified = driverService.verifyChangedEmail(mapBody.get("cipher"), SecurityContextHolder.getContext().getAuthentication());
        if (isVerified)
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, messageSource.getMessage("verified.success"
                    , null, LocaleContextHolder.getLocale())), HttpStatus.OK);

        return new ResponseEntity<Message>(new Message(MessageType.ERROR, messageSource.getMessage("verified.error"
                , null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }

}
