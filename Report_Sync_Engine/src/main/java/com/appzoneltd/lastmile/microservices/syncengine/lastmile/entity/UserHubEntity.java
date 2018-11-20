package com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "user_hub", schema = "lastmile_authorization_server")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "UserHubEntity.countAll", query = "SELECT COUNT(x) FROM UserHubEntity x") })
public class UserHubEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserEntity users;

	@Column(name = "hub_id")
	private Long hubId;
}
