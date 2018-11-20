package com.appzoneltd.lastmile.privilege.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="menu_item",schema="lastmile_authorization_server")
public class Menu {

	private Integer id;
	private String name;
	private String key;
	private String url;
	private boolean active;
	private Menu parent;
	private boolean base;
	private Integer theOrder;
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JoinColumn(name="name" , nullable=true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JoinColumn(name="key" , nullable=true)
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@JoinColumn(name="url" , nullable=true)
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@JoinColumn(name="active" , nullable=true)
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@JoinColumn(name="base" , nullable=true)
	public boolean isBase() {
		return base;
	}

	public void setBase(boolean base) {
		this.base = base;
	}

	@JoinColumn(name="theOrder" , nullable=true)
	public Integer getTheOrder() {
		return theOrder;
	}

	public void setTheOrder(Integer theOrder) {
		this.theOrder = theOrder;
	}
	
	
	
}
