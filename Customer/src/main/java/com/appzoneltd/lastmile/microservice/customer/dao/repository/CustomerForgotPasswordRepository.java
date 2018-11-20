package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CustomerForgotPasswordEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository : ForgotPassword.
 */
public interface CustomerForgotPasswordRepository extends CrudRepository<CustomerForgotPasswordEntity, Long> {

}
