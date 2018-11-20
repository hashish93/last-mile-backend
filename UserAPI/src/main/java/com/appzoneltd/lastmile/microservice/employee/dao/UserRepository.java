package com.appzoneltd.lastmile.microservice.employee.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.employee.entity.UserEntity;

/**
 * Repository : Users.
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	@Query("SELECT u from UserEntity u Where u.status = 'ACTIVE' AND u.userId = :userId")
	public UserEntity findActiveUserById(@Param("userId")Long userId);

	@Query("SELECT u from UserEntity u inner join u.usersRoles userRoles where u.status = 'ACTIVE' AND u.userId = :userId AND (userRoles.roles.enabled = true)")
	public UserEntity findUserWithActiveRolesById(@Param("userId")Long userId);

	@Query("SELECT DISTINCT u FROM UserEntity u WHERE (u.username = :identifier or u.email = :identifier or u.phone = :identifier)")
	public UserEntity findByUserIdentifier(@Param("identifier") String identifier);
	
	@Query("SELECT DISTINCT u FROM UserEntity u inner join u.userHubs userHub inner join u.userType userType inner join u.usersRoles userRoles WHERE  (userRoles.roles.editable = true) AND (userType.name = 'BACKOFFICE') AND (userHub.building.buildingId =:buildingId) AND ((LOWER(u.firstname) like CONCAT(LOWER(:name),'%')) OR (LOWER(u.lastname) like CONCAT(LOWER(:name),'%')) OR (LOWER(u.username) like CONCAT(LOWER(:name),'%'))) AND u.status='ACTIVE' ORDER BY u.id ASC")
	public List<UserEntity> searchUserByName(@Param("name") String name , @Param("buildingId") Long buildingId);
	
	@Query(value = "Select distinct u from UserEntity u inner join u.userType ut WHERE  ( str(u.userId) LIKE :key  OR u.username LIKE :key OR u.email LIKE :key OR u.phone LIKE :key OR lower(ut.name) LIKE :key) AND (ut.name<>'CUSTOMER' AND ut.adminUser=true)  ")
	List<UserEntity> findByKeyForAdmin(@Param("key") String key,Pageable pageable);
	
	@Query(value = "Select distinct u from UserEntity u inner join u.userType ut inner join u.userHubs uh inner join uh.building b WHERE  (b.buildingId IN (:hubs) ) AND ( str(u.userId) LIKE :key  OR u.username LIKE :key OR u.email LIKE :key OR u.phone LIKE :key OR lower(ut.name) LIKE :key) AND (ut.name<>'CUSTOMER' AND ut.normalUser=true )  ")
	List<UserEntity> findByKeyForUser(@Param("key") String key, @Param("hubs") List<Long> hubs,Pageable pageable);

	@Query("SELECT DISTINCT u FROM UserEntity u WHERE u.userId <> :userId AND u.username = :userName")
	UserEntity checkUserNameExists(@Param("userId") Long userId,@Param("userName") String userName);
	
	@Query("SELECT DISTINCT u FROM UserEntity u WHERE u.userId <> :userId AND u.nationalId = :nationalId")
	UserEntity checkNationalIdExists(@Param("userId") Long userId,@Param("nationalId") String nationalId);
	
	@Query("SELECT DISTINCT u FROM UserEntity u WHERE u.userId <> :userId AND u.email = :email")
	UserEntity checkEmailExists(@Param("userId") Long userId,@Param("email") String email);
	
	@Query("SELECT DISTINCT u FROM UserEntity u WHERE u.userId <> :userId AND u.phone = :phone")
	UserEntity checkPhoneExists(@Param("userId") Long userId,@Param("phone") String phone);
	
	@Query("SELECT u FROM UserEntity u inner join u.userType ut inner join u.userHubs uh inner join uh.building b WHERE ut.name = 'CORPORATE_DRIVER' AND b.buildingId IN (:hubs) ")
	List<UserEntity> getAllDrivers(@Param("hubs") List<Long> hubs,Pageable pageable);

	List<UserEntity> findAllByEmail(String email);

}
