package com.appzoneltd.lastmile.microservice.ums.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntityKey;

/**
 * Repository : UsersRoles.
 */
public interface UsersRolesRepository extends PagingAndSortingRepository<UserRoleEntity, UserRoleEntityKey> {

}
