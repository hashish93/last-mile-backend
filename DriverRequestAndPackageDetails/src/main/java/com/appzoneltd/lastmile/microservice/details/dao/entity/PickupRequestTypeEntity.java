/*
 * Created on 5 Jan 2017 ( Time 17:39:04 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.details.dao.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "pickup_request_type"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "pickup_request_type", schema = "lastmile_request")
@NamedQueries({
		@NamedQuery(name = "PickupRequestTypeEntity.countAll", query = "SELECT COUNT(x) FROM PickupRequestTypeEntity x") })
public class PickupRequestTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pickup_request_type_id", nullable = false)
	private Long pickupRequestTypeId;

	@Column(name = "type", nullable = false, length = 50)
	private String type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@OneToMany(mappedBy = "pickupRequestType", targetEntity = PickupRequestEntity.class)
	private List<PickupRequestEntity> listOfPickupRequest;

	public PickupRequestTypeEntity() {
		super();
	}

	public void setPickupRequestTypeId(Long pickupRequestTypeId) {
		this.pickupRequestTypeId = pickupRequestTypeId;
	}

	public Long getPickupRequestTypeId() {
		return this.pickupRequestTypeId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
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

	public void setListOfPickupRequest(List<PickupRequestEntity> listOfPickupRequest) {
		this.listOfPickupRequest = listOfPickupRequest;
	}

	public List<PickupRequestEntity> getListOfPickupRequest() {
		return this.listOfPickupRequest;
	}

}
