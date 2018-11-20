package com.appzoneltd.lastmile.microservice.freelancedriver.dao;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.UserEmailVerificationEntity;

/**
 * Repository : UserEmailVerification.
 */
public interface UserEmailVerificationJpaRepository extends PagingAndSortingRepository<UserEmailVerificationEntity, Long> {
    List<UserEmailVerificationEntity> findAllByVerificationCodeAndIsVerified(String verificationCode, Boolean isVerified);
}
