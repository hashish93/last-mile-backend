package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.DriverForgotPasswordEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Repository : DriverForgotPassword.
 */
public interface DriverForgotPasswordJpaRepository extends PagingAndSortingRepository<DriverForgotPasswordEntity, Long> {

}
