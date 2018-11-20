package com.appzoneltd.lastmile.microservice.calendar.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.calendar.entity.ActiveVehicleEntity;

public interface ActiveVehicleDao extends CrudRepository<ActiveVehicleEntity, Long> {

	@Query(value = "SELECT COUNT (id) FROM lastmile_core.active_vehicle v WHERE v.work_shift_id=:id", nativeQuery = true)
	public Long countAllActiveVehicleWithWorkShift(@Param("id") Long workShiftId);

}
