package com.appzoneltd.lastmile.microservice.workshift.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.workshift.dao.ActiveVehicleEntity;

public interface ActiveVehicleRepository extends CrudRepository<ActiveVehicleEntity, Long>{
	
	@Query(value="SELECT COUNT (id) FROM lastmile_core.active_vehicle v WHERE v.work_shift_id=:id",nativeQuery=true)
	public Long countAllActiveVehicleWithWorkShift(@Param("id") Long workShiftId);
	


}
