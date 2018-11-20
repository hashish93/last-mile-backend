package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "customer_age_summery", schema = "reporting")
public class CustomerAgeSummeryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

//	@Column(name = "within_period", length = 2147483647)
//	private String withinPeriod;

	@Column(name = "age_between_21_and_30")
	private Long ageBetween21And30;

	@Column(name = "age_less_than_21")
	private Long ageLessThan21;

	@Column(name = "age_between_31_and_45")
	private Long ageBetween31And45;

	@Column(name = "age_above_45")
	private Long ageAbove45;

	@Column(name = "others")
	private Long others;

	public CustomerAgeSummeryEntity() {
		this.ageLessThan21 = 0L;
		this.ageBetween21And30 = 0L;
		this.ageBetween31And45 = 0L;
		this.ageAbove45 = 0L;
		this.others = 0L;
	}
	
	
}
