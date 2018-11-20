package com.appzoneltd.lastmile.privilege.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.privilege.model.User;


@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	User findByIdAndStatus(Integer id, String status);
	
    public User findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.status='ACTIVE' AND u.id=:id")
    public User findByUserId(@Param("id") Integer id);
  
	@Query("SELECT u FROM User u WHERE (u.userName = :identifier or u.email = :identifier or u.phone = :identifier)")
	public User findByIdentifier(@Param("identifier") String identifier);

	@Query("SELECT u FROM User u WHERE ((u.firstName like CONCAT(:identifier,'%')) OR (u.lastName like CONCAT(:identifier,'%')) OR (u.userName like CONCAT(:identifier,'%'))) AND u.status='ACTIVE' AND u.userType='BACK_OFFICE_EMPLOYEE'")
	public List<User> searchUserByName(@Param("identifier") String identifier);
	
}