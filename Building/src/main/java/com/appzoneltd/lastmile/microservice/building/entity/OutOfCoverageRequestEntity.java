package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "out_of_coverage_request", schema = "lastmile_request")
public class OutOfCoverageRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "latitude", length = 2147483647)
	private String latitude;

	@Column(name = "longitude", length = 2147483647)
	private String longitude;

	@Column(name = "type", length = 2147483647)
	private String type;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;
}
