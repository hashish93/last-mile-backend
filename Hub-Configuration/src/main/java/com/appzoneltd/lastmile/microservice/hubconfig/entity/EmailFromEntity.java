package com.appzoneltd.lastmile.microservice.hubconfig.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
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

}
