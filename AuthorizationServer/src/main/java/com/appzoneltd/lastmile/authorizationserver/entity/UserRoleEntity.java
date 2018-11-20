
package com.appzoneltd.lastmile.authorizationserver.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users_roles", schema = "lastmile_authorization_server")
public class UserRoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleEntityKey compositePrimaryKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
	private RoleEntity roles;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	private UserEntity users;

}
