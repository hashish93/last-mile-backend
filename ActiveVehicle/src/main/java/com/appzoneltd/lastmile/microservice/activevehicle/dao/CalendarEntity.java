///*
// * Created on 20 Nov 2016 ( Time 10:17:25 )
// * Generated by Telosys Tools Generator ( version 2.1.1 )
// */
//// This Bean has a basic Primary Key (not composite) 
//
//package com.appzoneltd.lastmile.microservice.activevehicle.dao;
//
//import java.io.Serializable;
//
////import javax.validation.constraints.* ;
////import org.hibernate.validator.constraints.* ;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//
///**
// * Persistent class for entity stored in table "calendar"
// *
// * @author Telosys Tools Generator
// *
// */
//
//@Entity
//@Table(name = "calendar", schema = "lastmile_core")
//@NamedQueries({ @NamedQuery(name = "CalendarEntity.countAll", query = "SELECT COUNT(x) FROM CalendarEntity x") })
//public class CalendarEntity implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	@Id
//	@Column(name = "id", nullable = false)
//	private Long id;
//
//	@Column(name = "dayname", length = 50)
//	private String dayName;
//	
//	@Column(name = "status", nullable = false, length = 50)
//	private String status ;
//
//	@Temporal(value=TemporalType.TIMESTAMP)
//	@Column(name = "created" , insertable = false, updatable = false )
//	private Date created;
//
//    @Column(name = "version", nullable = false)
//	private Long version;
//
//	public CalendarEntity() {
//		super();
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getId() {
//		return this.id;
//	}
//
//
//	public String getDayName() {
//		return dayName;
//	}
//
//	public void setDayName(String dayName) {
//		this.dayName = dayName;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public void setCreated(Date created) {
//		this.created = created;
//	}
//
//	public Date getCreated() {
//		return this.created;
//	}
//
//	public void setVersion(Long version) {
//		this.version = version;
//	}
//
//	public Long getVersion() {
//		return this.version;
//	}
//
//
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("[");
//		sb.append(id);
//		sb.append("]:");
//		sb.append(dayName);
//		sb.append("|");
//		sb.append(created);
//		sb.append("|");
//		sb.append(version);
//		return sb.toString();
//	}
//
//}
