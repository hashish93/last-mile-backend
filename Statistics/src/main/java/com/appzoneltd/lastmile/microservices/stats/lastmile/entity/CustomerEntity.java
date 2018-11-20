package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

@Entity
@Table(name = "customer", schema = "lastmile_core")
@NamedQueries({ @NamedQuery(name = "CustomerEntity.countAll", query = "SELECT COUNT(x) FROM CustomerEntity x") })
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "gender", length = 50)
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")
	private Date birthdate;

	public CustomerEntity() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public CustomerEntity setCustomerId(Long customerId) {
		this.customerId = customerId;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public CustomerEntity setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public CustomerEntity setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CustomerEntity{");
		sb.append("customerId=").append(customerId);
		sb.append(", gender='").append(gender).append('\'');
		sb.append(", birthdate=").append(birthdate);
		sb.append('}');
		return sb.toString();
	}
}
