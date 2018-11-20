package com.appzoneltd.lastmile.microservice.workshift.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.workshift.dao.WorkShiftDto;
import com.appzoneltd.lastmile.microservice.workshift.dao.WorkShiftEntity;

public interface WorkShiftService {
	List<WorkShiftEntity> findAllWorkShifts();
	
	boolean isShiftFromExceedTo(Date from, Date to) ;
	
	boolean isShiftIdentical(Date from, Date to) ;
	
	HashSet<Long> savingValidAddedShifts(List<WorkShiftDto> workShiftDto);
	
	boolean chkShiftIntersected(WorkShiftDto firstShift , WorkShiftDto secondShift) ;
	
	Long deleteWorkShift(Long workShiftId)throws EntityNotFoundException ;
	
	boolean checkActiveVehicleWithinWorkShift (Long workShiftId) ;
	
	
}
