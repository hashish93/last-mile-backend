package com.appzoneltd.lastmile.microservice.details.dao.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import javax.persistence.*;

@Entity
@Table(name = "pickup_request_document", schema = "lastmile_request")
@NamedQueries({
		@NamedQuery(name = "PickupRequestDocumentEntity.countAll", query = "SELECT COUNT(x) FROM PickupRequestDocumentEntity x") })
public class PickupRequestDocumentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pickup_request_id", nullable = false)
	private Long pickupRequestId;

	@Column(name = "barcode", length = 100)
	private String barcode;

	@ManyToOne
	@JoinColumn(name = "creditcard_image_id", referencedColumnName = "content_id")
	private StaticContentEntity creditCardImage;

	@ManyToOne
	@JoinColumn(name = "customer_image_id", referencedColumnName = "content_id")
	private StaticContentEntity customerIdImage;

	@OneToOne
	@JoinColumn(name = "pickup_request_id", referencedColumnName = "pickup_request_id", insertable = false, updatable = false)
	private PickupRequestEntity pickupRequest;

	public PickupRequestDocumentEntity() {
		super();
	}

	public void setPickupRequestId(Long pickupRequestId) {
		this.pickupRequestId = pickupRequestId;
	}

	public Long getPickupRequestId() {
		return this.pickupRequestId;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setPickupRequest(PickupRequestEntity pickupRequest) {
		this.pickupRequest = pickupRequest;
	}

	public PickupRequestEntity getPickupRequest() {
		return this.pickupRequest;
	}

	public StaticContentEntity getCreditCardImage() {
		return creditCardImage;
	}

	public void setCreditCardImage(StaticContentEntity creditCardImage) {
		this.creditCardImage = creditCardImage;
	}

	public StaticContentEntity getCustomerIdImage() {
		return customerIdImage;
	}

	public void setCustomerIdImage(StaticContentEntity customerIdImage) {
		this.customerIdImage = customerIdImage;
	}

}
