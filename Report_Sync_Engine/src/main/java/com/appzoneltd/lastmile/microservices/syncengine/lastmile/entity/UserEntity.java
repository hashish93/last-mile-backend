package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "lastmile_authorization_server")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "username", length = 100)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "firstname", nullable = false, length = 100)
	private String firstname;

	@Column(name = "lastname", nullable = false, length = 100)
	private String lastname;

	@Column(name = "phone", nullable = false, length = 50)
	private String phone;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "personal_photo_id")
	private Long personalPhotoId;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "national_id", length = 100)
	private String nationalId;
	
	@OneToMany(mappedBy = "users", targetEntity = UserHubEntity.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<UserHubEntity> listOfUserHub;
}
