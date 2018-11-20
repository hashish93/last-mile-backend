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
@Table(name = "roles", schema = "lastmile_authorization_server")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id", nullable = false)
	private Long roleId;

	@Column(name = "rolename", nullable = false, length = 100)
	private String rolename;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "editable", nullable = false)
	private boolean editable = false;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "enabled")
	private boolean enabled = true;

	@OneToMany(mappedBy = "roles", targetEntity = RolePrivilegeEntity.class)
	private List<RolePrivilegeEntity> rolePrivileges;

	@ManyToOne
	@JoinColumn(name = "hub_id", referencedColumnName = "building_id")
	private BuildingEntity building;

	@OneToMany(mappedBy = "roles", targetEntity = UserRoleEntity.class)
	private List<UserRoleEntity> userRoles;
	
	@Column(name = "system_role", nullable = false)
	private boolean systemRole=false;
	
	@Override
	public String toString() {

		String result = "RoleEntity [roleId=" + roleId + ", rolename=" + rolename + ", created=" + created
				+ ", description=" + description + ", version=" + version + ", enabled=" + enabled + "]";

		result += " \nPRIVILEGES\n";

		for (RolePrivilegeEntity rolePrivilegeEntity : rolePrivileges) {
			result += " >> " + rolePrivilegeEntity.getModulePrivilege().getIdentifierName();
		}

		result += " \nUSERS\n ";

		for (UserRoleEntity userRoleEntity : userRoles) {
			result += " >> " + userRoleEntity.getUsers().getUsername();
		}

		result += " \nHUB\n ";

		if (building != null) {
			result += " >> " + building.getBuildingname();
		}

		return result;
	}

}
