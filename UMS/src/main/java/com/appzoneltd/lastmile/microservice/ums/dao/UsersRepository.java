package com.appzoneltd.lastmile.microservice.ums.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;

/**
 * Repository : Users.
 */
public interface UsersRepository extends PagingAndSortingRepository<UserEntity, Long> {

	@Query("SELECT u from UserEntity u Where u.status = 'ACTIVE' AND u.userId = :userId")
	public UserEntity findActiveUserById(@Param("userId")Long userId);

	@Query("SELECT u from UserEntity u inner join u.usersRoles userRoles where u.status = 'ACTIVE' AND u.userId = :userId AND (userRoles.roles.enabled = true)")
	public UserEntity findUserWithActiveRolesById(@Param("userId")Long userId);
	
	@Query("SELECT u FROM UserEntity u WHERE (u.username = :identifier or u.email = :identifier or u.phone = :identifier)")
	public UserEntity findByIdentifier(@Param("identifier") String identifier);
	
	@Query("SELECT DISTINCT u FROM UserEntity u WHERE (u.username = :identifier or u.email = :identifier or u.phone = :identifier)")
	public UserEntity findByUserIdentifier(@Param("identifier") String identifier);
	
	@Query("SELECT DISTINCT u FROM UserEntity u inner join u.userHubs userHub inner join u.userType userType inner join userHub.building b WHERE  (userType.adminUser = TRUE) AND (userType.name <>'CORPORATE_DRIVER') AND (b.buildingId IN (:hubs)) AND ((LOWER(u.firstname) like CONCAT(LOWER(:name),'%')) OR (LOWER(u.lastname) like CONCAT(LOWER(:name),'%')) OR (LOWER(u.username) like CONCAT(LOWER(:name),'%'))) AND u.status='ACTIVE' ORDER BY u.id ASC")
	public List<UserEntity> searchUserByNameWithAdmin(@Param("name") String name , @Param("hubs") List<Long> hubs);
	
	@Query("SELECT DISTINCT u FROM UserEntity u inner join u.userHubs userHub inner join u.userType userType inner join userHub.building b WHERE  (userType.normalUser = TRUE) AND (b.buildingId IN (:hubs)) AND ((LOWER(u.firstname) like CONCAT(LOWER(:name),'%')) OR (LOWER(u.lastname) like CONCAT(LOWER(:name),'%')) OR (LOWER(u.username) like CONCAT(LOWER(:name),'%'))) AND u.status='ACTIVE' ORDER BY u.id ASC")
	public List<UserEntity> searchUserByNameWithUser(@Param("name") String name , @Param("hubs") List<Long> hubs);
}
