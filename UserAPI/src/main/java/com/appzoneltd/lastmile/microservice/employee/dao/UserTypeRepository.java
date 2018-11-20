package com.appzoneltd.lastmile.microservice.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;

/**
 * Repository : UserType.
 */
public interface UserTypeRepository extends PagingAndSortingRepository<UserTypeEntity, Long> {

	@Query("SELECT u FROM UserTypeEntity u WHERE u.adminUser = true ORDER BY u.id ASC")
	List<UserTypeEntity> getAdminUserTypes();
	
	@Query("SELECT u FROM UserTypeEntity u WHERE u.normalUser = true ORDER BY u.id ASC")
	List<UserTypeEntity> getNormalUserTypes();
}
