package com.appzoneltd.lastmile.microservice.customer.service;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.customer.dto.*;
import com.appzoneltd.lastmile.microservice.customer.service.exception.InvalidPasswordMatchException;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Sort;

import java.security.Principal;
import java.util.List;

/**
 * @author alaa.nabil
 */
public interface CustomerService {

    Object saveCustomer(Customer customer) throws DuplicatedKeyException, JsonProcessingException;

    Object updateCustomer(Customer customer) throws DuplicatedKeyException, EntityNotFoundException;

    int deactivateCustomer(Long customerId) throws EntityNotFoundException;

    CustomerInfo findCustomerById(Long customerId) throws EntityNotFoundException;

    CustomerInfo findCustomerById(Principal principal) throws EntityNotFoundException;

    List<CustomerInfo> findAllCustomers(Sort sort);

    List<CustomerInfo> findAllCustomersByPageAndOffset(PagingParameters pagingParameters);

    Boolean verify(Principal principal, String code);

    Boolean changeNumber(Principal principal, UnverifiedNumber unverifiedNumber) throws EntityNotFoundException, JsonProcessingException;

    Boolean resendVerificationCode(Principal principal) throws EntityNotFoundException, JsonProcessingException;

    int countAllCustomers();

    UsersEntity changePassword(Principal principal, ChangePasswordModel changePasswordModel) throws EntityNotFoundException, InvalidOldPassword;

    Boolean forgotPassword(UnverifiedNumber unverifiedNumber) throws EntityNotFoundException, JsonProcessingException;

    boolean forgotPasswordVerifyToken(ForgetPasswordVerifyToken forgetPasswordVerifyToken) throws EntityNotFoundException, ExceededNumberOfAttempts, ForgotPasswordTokenExpired;

    boolean addNewPassword(NewPassword newPassword) throws EntityNotFoundException;

    UsersEntity changePasswordUnVerifyOld(Principal principal, NewChangePasswordModel changePasswordModel)
            throws EntityNotFoundException, InvalidPasswordMatchException;

    boolean updateMobileNumber(UnverifiedNumber unverifiedNumber, Principal principal) throws EntityNotFoundException, JsonProcessingException;

    Boolean verifyUpdatedNumber(Principal principal, UnverifiedNumber unverifiedNumber) throws EntityNotFoundException;

    Boolean updateEmail(String email, String password, Principal authentication) throws EntityNotFoundException, JsonProcessingException;

    Boolean verifyUpdatedEmail(String code, Principal principal);
}
