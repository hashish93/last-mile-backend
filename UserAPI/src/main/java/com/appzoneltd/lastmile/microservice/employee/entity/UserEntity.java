/*
 * Created on 3 Jul 2017 ( Time 14:38:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.employee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name = "personal_photo_id", nullable = false)
	private Long personalPhotoId;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "version", nullable = false)
	private Long version;
	
	@Column(name = "national_id", nullable = false, length = 100)
	private String nationalId;

	@OneToMany(mappedBy = "users", targetEntity = UserRoleEntity.class)
	private List<UserRoleEntity> usersRoles;

	@ManyToOne
	@JoinColumn(name = "user_type_id", referencedColumnName = "id", insertable = true, updatable = true)
	private UserTypeEntity userType;

	@OneToMany(mappedBy = "user", targetEntity = UserHubEntity.class)
	private List<UserHubEntity> userHubs;

	@ManyToOne
	@JoinColumn(name = "country_code_id", referencedColumnName = "id")
	private CountryCodesEntity countryCodes;

	@Override
	public String toString() {

		String result = "UserEntity [userId=" + userId + ", username=" + username + ", password=" + password
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", email=" + email
				+ ", enabled=" + enabled + ", created=" + created + ", status=" + status + ", description="
				+ description + ", version=" + version + "]";

		result += "User Type : " + userType.getName();

		result += "\nUSER ROLES ";

		for (UserRoleEntity userRoleEntity : usersRoles) {
			result += ">> ROLE NAME " + userRoleEntity.getRoles().getRolename();
		}

		result += " HUB ";
		for (UserHubEntity userHubEntity : userHubs) {
			result += ">> Building " + userHubEntity.getBuilding().getBuildingname();
		}

		return result;
	}

}
