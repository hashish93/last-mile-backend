package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : Users.
 */
public interface UsersJpaRepository extends PagingAndSortingRepository<UsersEntity, Long> {

    UsersEntity findByEmailOrPhone(String email, String phone);

    @Query(value = "SELECT u FROM com.appzoneltd.lastmile.microservice.details.dao.entity.UsersEntity u WHERE u.phone=:phone AND (u.userTypeId=6 OR u.userTypeId=7)")
    List<UsersEntity> findAllByPhoneAndUserTypeOrUserType(@Param("phone") String phone);

    List<UsersEntity> findAllByEmail(String email);
}
