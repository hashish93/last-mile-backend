package com.appzoneltd.lastmile.microservice.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.employee.entity.UserHubEntity;

/**
 * Repository : UserHub.
 */
public interface UserHubRepository extends PagingAndSortingRepository<UserHubEntity, Long> {
	
	@Query("SELECT  uh FROM UserHubEntity uh inner join uh.building building inner join uh.user u  inner join u.userType ut  WHERE building.buildingId = :hubId AND ut.name = 'HUB_ADMIN'")
	List<UserHubEntity> getUsersForHub(@Param("hubId") Long hubId);
	
	@Query("SELECT distinct u FROM UserHubEntity u inner join u.building building inner join u.user user WHERE building.buildingId = :hubId AND user.userId = :userId")
	UserHubEntity getUserHubForUserAndHub(@Param("userId") Long userId , @Param("hubId") Long hubId);
	
}
