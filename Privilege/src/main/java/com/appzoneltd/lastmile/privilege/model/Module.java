package com.appzoneltd.lastmile.privilege.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="module",schema="lastmile_authorization_server")
public class Module {

	private Integer id; 
	private String name;
	
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

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
