package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;


import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.UsersEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Users.
 */
public interface UsersRepository extends PagingAndSortingRepository<UsersEntity, Long> {

}
