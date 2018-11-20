package com.appzoneltd.lastmile.microservice.workshift.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
public class ActiveVehicleEntity {

	@Id
	@Column(name = "id", nullable = false)
	private Long id ; 
	
	@Column(name = "work_shift_id", nullable = false)
	private Long workShiftId ;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getWorkShiftId() {
		return workShiftId;
	}


	public void setWorkShiftId(Long workShiftId) {
		this.workShiftId = workShiftId;
	}

	
	
}
