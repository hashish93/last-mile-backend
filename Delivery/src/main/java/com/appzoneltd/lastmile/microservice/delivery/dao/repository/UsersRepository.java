package com.appzoneltd.lastmile.microservice.delivery.dao.repository;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.UsersEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Users.
 */
public interface UsersRepository extends PagingAndSortingRepository<UsersEntity, Long> {

    UsersEntity findByPhoneOrEmail(String phone, String email);

}
