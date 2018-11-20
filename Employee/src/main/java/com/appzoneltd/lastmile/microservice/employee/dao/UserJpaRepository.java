package com.appzoneltd.lastmile.microservice.employee.dao;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : Users.
 */
public interface UserJpaRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByUserId(Long userId);

	@Query(value = "Select distinct u from UserEntity u  inner join u.roles r WHERE  ( str(u.userId) LIKE :key  OR u.username LIKE :key OR u.email LIKE :key OR u.phone LIKE :key OR lower(u.userType) LIKE :key) AND (u.userType <>'CUSTOMER' AND u.status='ACTIVE' AND r.id<>1 )  ")
	List<UserEntity> findByKey(@Param("key") String key,Pageable pageable);

	@Query(value = "Select distinct u from UserEntity u inner join u.roles r WHERE  (u.username LIKE :key OR u.email LIKE :key OR u.phone LIKE :key OR lower(u.userType) LIKE :key) AND (u.userType <>'CUSTOMER' AND u.status='ACTIVE' AND r.id<>1 )  ")
	List<UserEntity> findCountByKey(@Param("key") String key);

}
