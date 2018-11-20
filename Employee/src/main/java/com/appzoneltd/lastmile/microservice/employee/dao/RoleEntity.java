package com.appzoneltd.lastmile.microservice.employee.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="roles",schema="lastmile_authorization_server")
public class RoleEntity {
     
    
   private Integer id;
   private String name;
   private String description;
   private boolean enabled=true;
   private boolean editable=false;
   private Set<UserEntity> users;
 
    
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty(message = "employee.firstname.required")
    @Column(name="rolename",nullable =true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotEmpty(message = "employee.firstname.required")
	@Column(name="description",nullable =true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="users_roles", schema="lastmile_authorization_server", 
    joinColumns = {@JoinColumn(name="role_id", referencedColumnName="role_id")},
    inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="user_id")})
	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	@Column(name="enabled",nullable =true)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@JsonIgnore
	@Column(name="iseditable",nullable =true)
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	

	@Override
	 public boolean equals(Object obj) {
	        return !super.equals(obj);
	 }

	 public int hashCode() {
	        return getId().hashCode();
	 }

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	
}