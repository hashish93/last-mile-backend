package com.appzoneltd.lastmile.microservices.syncengine.stats.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "sync_engine", schema = "reporting")
public class SyncEngineEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "module_name", length = 2147483647)
	private String moduleName;

	@Column(name = "time_interval")
	private Long timeInterval;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_sync_time")
	private Date lastSyncTime;

	@Column(name = "deleted")
	private boolean deleted;

}
