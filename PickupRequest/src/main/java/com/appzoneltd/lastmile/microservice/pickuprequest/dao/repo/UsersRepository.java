package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.UsersEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Users.
 */
public interface UsersRepository extends PagingAndSortingRepository<UsersEntity, Long> {
    UsersEntity findByPhoneAndUserTypeId(String phone, Long userTypeId);
}
