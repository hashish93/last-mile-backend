package com.appzoneltd.lastmile.microservice.building.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "system_config", schema = "lastmile_core")
public class SystemConfigEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "config_id", nullable = false)
	private Long configId;

	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "text_value", length = 100)
	private String textValue;

	@Column(name = "displayname", length = 50)
	private String displayname;

	@Column(name = "description", length = 250)
	private String description;

	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "unit", length = 2147483647)
	private String unit;
	
	@Column(name = "super_config", length = 2147483647)
	private boolean superConfig;
	
	@Column(name = "config_type", length = 100)
	private String configType;

//	@OneToMany(mappedBy = "systemConfig", targetEntity = HubConfigurationEntity.class)
//	private List<HubConfigurationEntity> listOfHubConfiguration;
}
