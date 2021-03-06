/*
 * Created on 3 Jul 2017 ( Time 14:38:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.activevehicle.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "user_type", schema = "lastmile_authorization_server")
public class UserTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", length = 2147483647)
	private String name;
	
	@Column(name = "normal_user")
	private Boolean normalUser;

	@Column(name = "admin_user")
	private Boolean adminUser;

	
}
