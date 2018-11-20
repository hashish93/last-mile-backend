package com.appzoneltd.lastmile.microservice.customer.controller;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CustomerEntity;
import com.appzoneltd.lastmile.microservice.customer.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.customer.dto.*;
import com.appzoneltd.lastmile.microservice.customer.model.CustomerPackageStatisticsModel;
import com.appzoneltd.lastmile.microservice.customer.model.CustomerStatistics;
import com.appzoneltd.lastmile.microservice.customer.service.CustomerRequestStatisticsService;
import com.appzoneltd.lastmile.microservice.customer.service.CustomerService;
import com.appzoneltd.lastmile.microservice.customer.service.ExceededNumberOfAttempts;
import com.appzoneltd.lastmile.microservice.customer.service.ForgotPasswordTokenExpired;
import com.appzoneltd.lastmile.microservice.customer.service.InvalidOldPassword;
import com.appzoneltd.lastmile.microservice.customer.service.exception.InvalidPasswordMatchException;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.Map;

/**
 * @author alaa.nabil
 */
@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;
    private MessageSource messageSource;
    
    @Autowired
    private CustomerRequestStatisticsService customerRequestStatisticsService;

    @Autowired
    public CustomerController(CustomerService customerService, MessageSource messageSource) {
        this.customerService = customerService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer) throws DuplicatedKeyException, JsonProcessingException {
        Object result = customerService.saveCustomer(customer);
        if (result instanceof Integer) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "created", messageSource.getMessage("customer.create.success",
                            null, "customer.create.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/getCustomerPackagesStatistics", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerPackagesStatistics(@RequestBody EndPointParameter endPointParameter){
        if(endPointParameter != null && endPointParameter.getId() != null){
        	List<CustomerStatistics> customerStatistics = 
        			customerRequestStatisticsService.getCustomerPackagesStatistics(endPointParameter.getId());
        	if(customerStatistics != null){
        		 return new ResponseEntity<>(customerStatistics, HttpStatus.OK);
        	}
        }
    	
        return new ResponseEntity<Message>(
                new Message(MessageType.ERROR, "customerId", messageSource.getMessage("customer.customerId.notfound",
                        null, "customer.customerId.notfound", LocaleContextHolder.getLocale())),
                HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/getCustomerPackageTypeStatistics", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerPackageTypeStatistics(@RequestBody EndPointParameter endPointParameter){
        if(endPointParameter != null && endPointParameter.getId() != null){
        	List<CustomerPackageStatisticsModel> customerPackageStatistics = 
        			customerRequestStatisticsService.getCustomerPackageTypeStatistics(endPointParameter.getId());
        	if(customerPackageStatistics != null){
        		 return new ResponseEntity<>(customerPackageStatistics, HttpStatus.OK);
        	}
        }
    	
        return new ResponseEntity<Message>(
                new Message(MessageType.ERROR, "customerId", messageSource.getMessage("customer.customerId.notfound",
                        null, "customer.customerId.notfound", LocaleContextHolder.getLocale())),
                HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomer(@Validated @RequestBody Customer customer)
            throws DuplicatedKeyException, EntityNotFoundException {

        Object result = customerService.updateCustomer(customer);
        if (result != null && result instanceof CustomerEntity) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("customer.update.success",
                            null, "customer.update.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateMobileNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMobileNumber(@Validated @RequestBody UnverifiedNumber unverifiedNumber)
            throws DuplicatedKeyException, EntityNotFoundException, JsonProcessingException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        boolean result = customerService.updateMobileNumber(unverifiedNumber, principal);
        if (result) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("customer.update.success",
                            null, "customer.update.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmail(@Validated @RequestBody NewEmail newEmail)
            throws DuplicatedKeyException, EntityNotFoundException, JsonProcessingException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        boolean result = customerService.updateEmail(newEmail.getEmail(), newEmail.getPassword(), principal);
        if (result) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("customer.update.success",
                            null, "customer.update.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verifyUpdatedEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> verifyCustomer(@RequestBody Map<String, String> body)
            throws EntityNotFoundException {
        Boolean isVerified = customerService.verifyUpdatedEmail(body.get("code"), SecurityContextHolder.getContext().getAuthentication());
        if (isVerified)
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, messageSource.getMessage("verified.success"
                    , null, LocaleContextHolder.getLocale())), HttpStatus.OK);

        return new ResponseEntity<Message>(new Message(MessageType.ERROR, messageSource.getMessage("verified.error"
                , null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verifyUpdatedMobile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> verifyCustomer(@Validated @RequestBody UnverifiedNumber unverifiedNumber)
            throws EntityNotFoundException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Boolean isVerified = customerService.verifyUpdatedNumber(principal, unverifiedNumber);
        if (isVerified)
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, messageSource.getMessage("verified.success"
                    , null, LocaleContextHolder.getLocale())), HttpStatus.OK);

        return new ResponseEntity<Message>(new Message(MessageType.ERROR, messageSource.getMessage("verified.error"
                , null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@Validated @RequestBody ChangePasswordModel changePasswordModel)
            throws DuplicatedKeyException, EntityNotFoundException {

        UsersEntity usersEntity = null;
        try {
            Principal principal = SecurityContextHolder.getContext().getAuthentication();
            usersEntity = customerService.changePassword(principal, changePasswordModel);
        } catch (InvalidOldPassword invalidOldPassword) {
            return new ResponseEntity<>(new Message(MessageType.ERROR, invalidOldPassword.getMessage()), HttpStatus.BAD_REQUEST);
        }
        if (usersEntity != null) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("password.update.success",
                            null, "password.update.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(usersEntity, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/changePasswordNew", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@Validated @RequestBody NewChangePasswordModel changePasswordModel)
            throws DuplicatedKeyException, EntityNotFoundException {

        UsersEntity usersEntity = null;
        try {
            Principal principal = SecurityContextHolder.getContext().getAuthentication();
            usersEntity = customerService.changePasswordUnVerifyOld(principal,
                    changePasswordModel);

        } catch (InvalidPasswordMatchException e) {

            new ResponseEntity<>(new Message(MessageType.ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        if (usersEntity != null) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, "updated", messageSource.getMessage("password.update.success",
                            null, "password.update.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(usersEntity, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forgotPassword(@RequestBody UnverifiedNumber unverifiedNumber)
            throws EntityNotFoundException, JsonProcessingException {
        if (customerService.forgotPassword(unverifiedNumber)) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/forgotPasswordVerifyToken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> forgotPasswordVerifyToken(@RequestBody ForgetPasswordVerifyToken forgetPasswordVerifyToken)
            throws EntityNotFoundException, JsonProcessingException {
        try {
            if (!customerService.forgotPasswordVerifyToken(forgetPasswordVerifyToken)) {
                return new ResponseEntity<>(
                        new Message(MessageType.ERROR, messageSource.getMessage("invalid.forgot.password.token", null, LocaleContextHolder.getLocale())),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (ExceededNumberOfAttempts exceededNumberOfAttempts) {
            return new ResponseEntity<>(
                    new Message(MessageType.ERROR, exceededNumberOfAttempts.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (ForgotPasswordTokenExpired forgotPasswordTokenExpired) {
            return new ResponseEntity<>(
                    new Message(MessageType.ERROR, forgotPasswordTokenExpired.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                new Message(MessageType.SUCCESS, null)
                , HttpStatus.OK);
    }

    @RequestMapping(value = "/addNewPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewPassword(@RequestBody NewPassword newPassword)
            throws EntityNotFoundException, JsonProcessingException {
        if (customerService.addNewPassword(newPassword)) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerInfo>> customerFindAll(@RequestBody PagingParameters requestParameter) {
        List<CustomerInfo> customers = null;
        try {
            if (requestParameter.getPage() <= 0)
                return new ResponseEntity<List<CustomerInfo>>(customerService.findAllCustomers(requestParameter.getSort()), HttpStatus.OK);
            else
                return new ResponseEntity<List<CustomerInfo>>(customerService.findAllCustomersByPageAndOffset(requestParameter), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> countAllCustomer() {
        int countAll = customerService.countAllCustomers();
        return new ResponseEntity<Object>(new Message(MessageType.SUCCESS, Integer.toString(countAll),
                messageSource.getMessage("customer.countall.success", null, "customer.countall.success",
                        LocaleContextHolder.getLocale())),
                (HttpStatus.OK));

    }

    @RequestMapping(method = RequestMethod.POST, value = "/deactivetcustomer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deactivateCsutomer(@RequestBody EndPointParameter requestParameter)
            throws EntityNotFoundException {
        customerService.deactivateCustomer(requestParameter.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getcustomerbyid", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerInfo> getCustomerByID(@RequestBody EndPointParameter requestParameter)
            throws EntityNotFoundException {
        CustomerInfo customer = customerService.findCustomerById(requestParameter.getId());
        return new ResponseEntity<CustomerInfo>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customerDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerInfo> getCustomerByPrincipal()
            throws EntityNotFoundException {
        CustomerInfo customer = customerService.findCustomerById(SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<CustomerInfo>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> verifyCustomer(@RequestBody CustomerVerification customerVerification)
            throws EntityNotFoundException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Boolean isVerified = customerService.verify(principal, customerVerification.getCode());
        if (isVerified)
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, messageSource.getMessage("verified.success"
                    , null, LocaleContextHolder.getLocale())), HttpStatus.OK);

        return new ResponseEntity<Message>(new Message(MessageType.ERROR, messageSource.getMessage("verified.error"
                , null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/changeNumber", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> changeUnverifiedNumber(@RequestBody @Validated UnverifiedNumber unverifiedNumber)
            throws EntityNotFoundException, JsonProcessingException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Boolean result = customerService.changeNumber(principal, unverifiedNumber);
        if (result)
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, messageSource.getMessage("verification.code.success"
                    , null, LocaleContextHolder.getLocale())), HttpStatus.OK);

        return new ResponseEntity<Message>(new Message(MessageType.ERROR, messageSource.getMessage("verification.code.error"
                , null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/resendVerificationCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> resendVerificationCode()
            throws EntityNotFoundException, JsonProcessingException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Boolean result = customerService.resendVerificationCode(principal);
        if (result)
            return new ResponseEntity<>(new Message(MessageType.SUCCESS,
                    messageSource.getMessage("verification.code.success", null, LocaleContextHolder.getLocale())), HttpStatus.OK);
        return new ResponseEntity<Message>(new Message(MessageType.ERROR, messageSource.getMessage("verification.code.error"
                , null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }
}
