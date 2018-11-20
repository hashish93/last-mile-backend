package com.appzoneltd.lastmile.microservice.employee.entity;

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
@Table(name = "role_privilege", schema = "lastmile_authorization_server")
public class RolePrivilegeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@Column(name = "version", nullable = false)
	private Long version;

	@ManyToOne
	@JoinColumn(name = "module_privilege_id", referencedColumnName = "id")
	private ModulePrivilegeEntity modulePrivilege;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private RoleEntity roles;
	
}
