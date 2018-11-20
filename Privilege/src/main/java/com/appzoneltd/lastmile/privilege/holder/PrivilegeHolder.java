package com.appzoneltd.lastmile.privilege.holder;

public class PrivilegeHolder {

	private String name;
	private boolean active;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "PrivilegeHolder [name=" + name + ", active=" + active + "]";
	}
	
	
	
}
