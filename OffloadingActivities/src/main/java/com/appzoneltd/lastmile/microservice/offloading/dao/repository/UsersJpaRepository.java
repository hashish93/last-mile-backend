package com.appzoneltd.lastmile.microservice.offloading.dao.repository;

import com.appzoneltd.lastmile.microservice.offloading.dao.entity.UsersEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Users.
 */
public interface UsersJpaRepository extends PagingAndSortingRepository<UsersEntity, Long> {

}
