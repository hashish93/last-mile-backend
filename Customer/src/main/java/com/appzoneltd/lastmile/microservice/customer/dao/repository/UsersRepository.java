package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : Users.
 */
public interface UsersRepository extends PagingAndSortingRepository<UsersEntity, Long> {
    @Query(value = "SELECT u FROM UsersEntity u WHERE u.phone=:phone AND u.userTypeEntity.name='CUSTOMER'")
    List<UsersEntity> findAllByPhone(@Param("phone") String phone);

    List<UsersEntity> findAllByEmail(String email);

    UsersEntity findByPhoneOrEmail(String phone, String email);
}
