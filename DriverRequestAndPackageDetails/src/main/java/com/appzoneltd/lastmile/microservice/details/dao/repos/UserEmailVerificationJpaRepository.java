package com.appzoneltd.lastmile.microservice.details.dao.repos;


import com.appzoneltd.lastmile.microservice.details.dao.entity.UserEmailVerificationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository : UserEmailVerification.
 */
public interface UserEmailVerificationJpaRepository extends PagingAndSortingRepository<UserEmailVerificationEntity, Long> {
    List<UserEmailVerificationEntity> findAllByVerificationCodeAndIsVerified(String verificationCode, Boolean isVerified);
}
