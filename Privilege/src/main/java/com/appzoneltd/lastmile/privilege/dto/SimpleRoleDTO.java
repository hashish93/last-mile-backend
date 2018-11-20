package com.appzoneltd.lastmile.privilege.dto;

public class SimpleRoleDTO {

	private Integer id;
	private String name;
	
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
	
	@Override
	public String toString() {
		return "ActiveRolesDTO [id=" + id + ", name=" + name + "]";
	}
	
	
	
}//end class ActiveDTO
