package com.appzoneltd.lastmile.privilege.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="privilege",schema="lastmile_authorization_server")
public class Privilege {

	private Integer id; 
	private String name;
	private String displayName;
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name",nullable =true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="displayname",nullable =true)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + ", displayName=" + displayName + "]";
	}
	
	
}
