package com.appzoneltd.lastmile.authorizationserver.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.authorizationserver.entity.UserEntity;


@Transactional
@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {

    public UserEntity findUserByEmail(String email);
  
    public UserEntity findByUserId(Long UserId);
  
	@Query("SELECT u FROM UserEntity u WHERE (u.username = :identifier or u.email = :identifier or u.phone = :identifier)")
	public UserEntity findByIdentifier(@Param("identifier") String identifier);

}