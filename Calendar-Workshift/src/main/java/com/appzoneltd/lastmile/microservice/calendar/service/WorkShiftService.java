package com.appzoneltd.lastmile.microservice.calendar.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.calendar.dao.ActiveVehicleDao;
import com.appzoneltd.lastmile.microservice.calendar.dao.BuildingDao;
import com.appzoneltd.lastmile.microservice.calendar.dao.WorkShiftDao;
import com.appzoneltd.lastmile.microservice.calendar.dto.WorkShiftDto;
import com.appzoneltd.lastmile.microservice.calendar.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.calendar.entity.WorkShiftEntity;
import com.appzoneltd.lastmile.microservice.calendar.transformer.WorkShiftTransformer;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;

/**
 * @author Khaled & Hashish
 *
 */
@Service
public class WorkShiftService {

	@Autowired
	private WorkShiftDao workShiftDao;

	@Autowired
	private ActiveVehicleDao activeVehicleDao;
	
	@Autowired
	private PrincipalService principalService;
	
	@Autowired
	private BuildingDao buildingDao;

	public List<WorkShiftDto> findAllWorkShifts(Long hubId , Principal principal) {
		List<WorkShiftEntity> workShiftEntities = new ArrayList<>();
		if (!principalService.isSuperAdmin(principal) && !principalService.isSuperVisor(principal)) {
			hubId = principalService.getHubIdIfFound(principal);
		} 
		if (hubId != null) {
			workShiftEntities = workShiftDao.findAllWorkShiftsForHubId(hubId);
		}
		return WorkShiftTransformer.getWorkShiftDtosFromEntities(workShiftEntities);
	}

	public HashSet<Long> savingValidAddedShifts(List<WorkShiftDto> workShifts,Principal principal) {
		HashSet<Long> shiftIds = new HashSet<Long>();
		for (WorkShiftDto firstShift : workShifts) {
			if (!isShiftFromExceedTo(firstShift.getFrom(), firstShift.getTo())) {
				for (WorkShiftDto secondshift2 : workShifts) {
					if (!firstShift.equals(secondshift2)) {
						if (chkShiftIntersected(firstShift, secondshift2)) {
							shiftIds.add(firstShift.getId());
							shiftIds.add(secondshift2.getId());

						}
					}
				}
			}

			else {
				shiftIds.add(firstShift.getId());
			}
		}

		if (shiftIds.isEmpty()) {
			saveShifts(workShifts, principal);
		}

		return shiftIds;
	}

	public void saveShifts(List<WorkShiftDto> workShifts,Principal principal) {
		Long hubId = null;
		for (WorkShiftDto workShiftDto : workShifts) {
			if(principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)){
				hubId = workShiftDto.getHubId();
			}else{
				hubId = principalService.getHubIdIfFound(principal);
			}
			WorkShiftEntity workShiftEntity = new WorkShiftEntity();
			workShiftEntity.setId(workShiftDto.getId());
			workShiftEntity.setDateFrom(workShiftDto.getFrom());
			workShiftEntity.setDateTo(workShiftDto.getTo());
			workShiftEntity.setCreated(new Date());
			if(hubId !=null){
				BuildingEntity buildingEntity = buildingDao.findOne(hubId);
				workShiftEntity.setBuilding(buildingEntity);
			}			workShiftEntity.setVersion(workShiftDto.getVersion());
			workShiftDao.save(workShiftEntity);
		}	
	}

	public boolean chkShiftIntersected(WorkShiftDto firstShift, WorkShiftDto secondShift) {
		if (isShiftFromExceedTo(firstShift.getFrom(), secondShift.getFrom())
				&& isShiftFromExceedTo(secondShift.getTo(), firstShift.getFrom())
				|| isShiftFromExceedTo(secondShift.getFrom(), firstShift.getFrom())
						&& isShiftFromExceedTo(firstShift.getTo(), secondShift.getFrom())
				|| isShiftIdentical(firstShift.getFrom(), secondShift.getFrom())
				|| isShiftIdentical(secondShift.getFrom(), firstShift.getFrom())) {
			return true;
		}

		return false;
	}

	public boolean isShiftFromExceedTo(Date from, Date to) {

		if (to.before(from)) {
			return true;
		}
		return false;
	}

	public boolean isShiftIdentical(Date from, Date to) {

		if (to.equals(from)) {
			return true;
		}
		return false;
	}

	public boolean checkActiveVehicleWithinWorkShift(Long workShiftId) {
		boolean chkActiveVehicleWithinWorkShift = false;
		Long result = activeVehicleDao.countAllActiveVehicleWithWorkShift(workShiftId);
		if (result != 0) {
			chkActiveVehicleWithinWorkShift = true;
		}
		return chkActiveVehicleWithinWorkShift;
	}

	public Long deleteWorkShift(Long workShiftId) throws EntityNotFoundException {

		workShiftDao.delete(workShiftId);

		return workShiftId;
	}

}
