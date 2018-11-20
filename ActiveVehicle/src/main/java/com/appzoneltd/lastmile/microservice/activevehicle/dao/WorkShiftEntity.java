/*
 * Created on 20 Nov 2016 ( Time 10:17:25 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.activevehicle.dao;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Persistent class for entity stored in table "work_shift"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "work_shift", schema = "lastmile_core")
@NamedQueries({ @NamedQuery(name = "WorkShiftEntity.countAll", query = "SELECT COUNT(x) FROM WorkShiftEntity x") })
public class WorkShiftEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "date_from")
	private Date from;

	@Column(name = "date_to")
	private Date to;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "created", insertable = false, updatable = false )
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;
	

	public WorkShiftEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return this.version;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(id);
		sb.append("]:");
		sb.append(from);
		sb.append("|");
		sb.append(to);
		sb.append("|");
		sb.append(created);
		sb.append("|");
		sb.append(version);
		return sb.toString();
	}
}
