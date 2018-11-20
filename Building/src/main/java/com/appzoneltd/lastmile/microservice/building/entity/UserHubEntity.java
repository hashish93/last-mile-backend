package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_hub", schema = "lastmile_authorization_server")
public class UserHubEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UsersEntity users;

	@ManyToOne
	@JoinColumn(name = "hub_id", referencedColumnName = "building_id")
	private BuildingEntity building;
}
