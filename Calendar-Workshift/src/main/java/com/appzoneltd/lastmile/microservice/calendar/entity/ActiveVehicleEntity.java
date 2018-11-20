package com.appzoneltd.lastmile.microservice.calendar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
public class ActiveVehicleEntity {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "work_shift_id", nullable = false)
	private Long workShiftId;

}
