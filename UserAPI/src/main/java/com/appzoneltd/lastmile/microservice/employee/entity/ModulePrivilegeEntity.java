package com.appzoneltd.lastmile.microservice.employee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name = "module_privilege", schema = "lastmile_authorization_server")
public class ModulePrivilegeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "identifier_name", nullable = false, length = 100)
	private String identifierName;

	@Column(name = "default_value")
	private boolean defaultValue=false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;
	
	@ManyToOne
	@JoinColumn(name = "module_id", referencedColumnName = "id")
	private ModuleEntity module;

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	private ModulePrivilegeEntity parent;

	@ManyToOne
	@JoinColumn(name = "privilege_id", referencedColumnName = "id")
	private PrivilegeEntity privilege;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	@Where(clause = "parent IS NOT NULL")
	private List<ModulePrivilegeEntity> child;

	// @OneToMany(mappedBy = "modulePrivilege", targetEntity =
	// RolePrivilegeEntity.class)
	// private List<RolePrivilegeEntity> listOfRolePrivilege;
}
