package com.appzoneltd.lastmile.microservice.forgetpassword.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "forget_password", schema = "lastmile_authorization_server")
@Data
public class ForgetPasswordEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "email", nullable = false, length = 200)
	private String email;

	@Column(name = "code_generated", nullable = false, length = 200)
	private String codeGenerated;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

}
