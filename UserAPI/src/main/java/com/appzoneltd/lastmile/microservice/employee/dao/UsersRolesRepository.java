package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.UserRoleEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserRoleEntityKey;

/**
 * Repository : UsersRoles.
 */
public interface UsersRolesRepository extends PagingAndSortingRepository<UserRoleEntity, UserRoleEntityKey> {

}
