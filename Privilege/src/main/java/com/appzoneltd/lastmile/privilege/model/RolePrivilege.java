package com.appzoneltd.lastmile.privilege.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="role_privilege",schema="lastmile_authorization_server")
public class RolePrivilege {

	private Integer id;
	private ModulePrivilege  modulePrivilege; 
	private Role role;
	private boolean enabled=false;
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@JsonProperty("permissions")
	@JoinColumn(name="module_privilege_id" , nullable=true)
	@ManyToOne(fetch = FetchType.EAGER,cascade = javax.persistence.CascadeType.ALL)
	public ModulePrivilege getModulePrivilege() {
		return modulePrivilege;
	}
	
	public void setModulePrivilege(ModulePrivilege modulePrivilege) {
		this.modulePrivilege = modulePrivilege;
	}
	
	@JsonIgnore
	@JoinColumn(name="role_id" , nullable=true)
	@ManyToOne(fetch = FetchType.EAGER,cascade = javax.persistence.CascadeType.ALL)
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column(name="enabled",nullable =true)
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "RolePrivilege [id=" + id + ", modulePrivilege=" + modulePrivilege + ", role=" + role + ", enabled="
				+ enabled + "]";
	}
	
}
