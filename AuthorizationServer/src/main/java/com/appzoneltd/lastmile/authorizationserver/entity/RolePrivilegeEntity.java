/*
 * Created on 3 Jul 2017 ( Time 14:38:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.authorizationserver.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Persistent class for entity stored in table "role_privilege"
 *
 * @author Telosys Tools Generator
 *
 */

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "module_privilege_id", referencedColumnName = "id")
	private ModulePrivilegeEntity modulePrivilege;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private RoleEntity roles;
	
}
