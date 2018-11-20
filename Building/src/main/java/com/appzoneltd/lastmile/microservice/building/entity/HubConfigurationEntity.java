package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "hub_configuration", schema = "lastmile_core")
public class HubConfigurationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "text_value", length = 100)
	private String textValue;

	@Temporal(TemporalType.DATE)
	@Column(name = "created")
	private Date created;

	@Column(name = "config_type", length = 100)
	private String configType;
	
	@ManyToOne
	@JoinColumn(name = "system_config_id", referencedColumnName = "config_id")
	private SystemConfigEntity systemConfig;

	@ManyToOne
	@JoinColumn(name = "hub_id", referencedColumnName = "building_id")
	private BuildingEntity building;
}
