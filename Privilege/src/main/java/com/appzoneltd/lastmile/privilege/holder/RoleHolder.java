package com.appzoneltd.lastmile.privilege.holder;

import java.util.List;

public class RoleHolder {

	private Integer id;
	private String name;
	private List<PrivilegeHolder> privileges;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<PrivilegeHolder> getPrivileges() {
		return privileges;
	}
	
	public void setPrivileges(List<PrivilegeHolder> privileges) {
		this.privileges = privileges;
	}
	
	@Override
	public String toString() {
		return "RoleHolder [id=" + id + ", name=" + name + ", privileges=" + privileges + "]";
	} 
	
	
	
}
