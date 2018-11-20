package com.appzoneltd.lastmile.microservice.workshift.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.workshift.dao.ActiveVehicleRepository;
import com.appzoneltd.lastmile.microservice.workshift.dao.WorkShiftDto;
import com.appzoneltd.lastmile.microservice.workshift.dao.WorkShiftEntity;
import com.appzoneltd.lastmile.microservice.workshift.dao.WorkShiftRepository;


/**
 * @author alaa.nabil
 *
 */
@Service
public class WorkShiftServiceImp implements WorkShiftService {

	private final WorkShiftRepository workShiftRepository;

	private final ActiveVehicleRepository activeVehicleRepository;

	public WorkShiftServiceImp(WorkShiftRepository calendarRepository,
			ActiveVehicleRepository activeVehicleRepository) {
		this.workShiftRepository = calendarRepository;
		this.activeVehicleRepository = activeVehicleRepository;
	}

	@Override
	public List<WorkShiftEntity> findAllWorkShifts() {
		return  workShiftRepository.findAllWorkShifts();

	}

	@Override
	public HashSet<Long> savingValidAddedShifts(List<WorkShiftDto> workShifts) {
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
			saveShifts(workShifts);
		}

		return shiftIds;
	}

	
	public void saveShifts(List<WorkShiftDto> workShifts) {
		List<WorkShiftEntity> shifts = new ArrayList<WorkShiftEntity>();
		for (WorkShiftDto workShiftDto : workShifts) {
			WorkShiftEntity workShiftEntity = new WorkShiftEntity();
			workShiftEntity.setId(workShiftDto.getId());
			workShiftEntity.setFrom(workShiftDto.getFrom());
			workShiftEntity.setTo(workShiftDto.getTo());
			workShiftEntity.setVersion(workShiftDto.getVersion());	
			shifts.add(workShiftEntity);
		}
		for(WorkShiftEntity shift:shifts){
			workShiftRepository.save(shift);
		}
		
	}

	@Override
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

	@Override
	public boolean isShiftFromExceedTo(Date from, Date to) {

		if (to.before(from)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isShiftIdentical(Date from, Date to) {

		if (to.equals(from)) {
			return true;
		}
		return false;
	}

	/*
	 * Delete Shift
	 */

	public boolean checkActiveVehicleWithinWorkShift(Long workShiftId) {
		boolean chkActiveVehicleWithinWorkShift = false;
		Long result = activeVehicleRepository.countAllActiveVehicleWithWorkShift(workShiftId);
		if (result != 0) {
			chkActiveVehicleWithinWorkShift = true;
		}
		return chkActiveVehicleWithinWorkShift;
	}

	@Override
	public Long deleteWorkShift(Long workShiftId) throws EntityNotFoundException {

		workShiftRepository.delete(workShiftId);

		return workShiftId;
	}

}
