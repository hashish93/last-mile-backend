
package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

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

	@Column(name = "firstname", nullable = false, length = 100)
	private String firstname;

	@Column(name = "lastname", nullable = false, length = 100)
	private String lastname;

	@Column(name = "phone", nullable = false, length = 50)
	private String phone;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "personal_photo_id", nullable = false, length = 50)
	private Long imageId;

	@ManyToOne
	@JoinColumn(name = "user_type_id", referencedColumnName = "id", insertable = false, updatable = false)
	private UserTypeEntity userType;

}
