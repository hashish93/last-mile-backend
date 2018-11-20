package com.appzoneltd.lastmile.microservice.employee.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_type", schema = "lastmile_authorization_server")
public class UserTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", length = 2147483647)
	private String name;

	@Column(name = "normal_user")
	private Boolean normalUser;

	@Column(name = "admin_user")
	private Boolean adminUser;

	@OneToMany(mappedBy = "userType", targetEntity = UserEntity.class)
	private List<UserEntity> listOfUsers;

	@Override
	public String toString() {
		return "UserTypeEntity [id=" + id + ", name=" + name + ", normalUser=" + normalUser + ", adminUser=" + adminUser
				+ "]";
	}
	
}
