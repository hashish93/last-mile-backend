package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.DriverForgotPasswordEntity;


/**
 * Repository : DriverForgotPassword.
 */
public interface DriverForgotPasswordJpaRepository extends PagingAndSortingRepository<DriverForgotPasswordEntity, Long> {

}
