/*
 * Created on 12 Jul 2017 ( Time 12:05:11 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.employee.dao;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "email_from"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "email_from", schema = "lastmile_core")
@NamedQueries({ @NamedQuery(name = "EmailFromEntity.countAll", query = "SELECT COUNT(x) FROM EmailFromEntity x") })
public class EmailFromEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email", length = 1000)
	private String email;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(id);
		sb.append("]:");
		sb.append(email);

		return sb.toString();
	}

}
