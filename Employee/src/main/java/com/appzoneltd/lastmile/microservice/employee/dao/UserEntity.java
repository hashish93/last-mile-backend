/*
 * Created on 4 Jan 2017 ( Time 17:47:57 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.employee.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "users"
 *
 * @author Telosys Tools Generator
 *
 */
@Data
@Entity
@Table(name = "users", schema = "lastmile_authorization_server")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "firstname", nullable = false, length = 100)
	private String firstname;

	@Column(name = "lastname", nullable = false, length = 100)
	private String lastname;

	@Column(name = "country_code_id", nullable = false)
	private Long countryCodeId;

	@Column(name = "phone", nullable = false, length = 50)
	private String phone;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "user_type", nullable = false, length = 100)
	private String userType;

	@Column(name = "personal_photo_id")
	private Long personalPhotoId;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="users_roles" , schema="lastmile_authorization_server",
			joinColumns = {@JoinColumn(name="user_id", referencedColumnName="user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="role_id")})
	private Set<RoleEntity> roles;
}
