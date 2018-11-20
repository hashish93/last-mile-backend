package com.appzoneltd.lastmile.microservice.calendar.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.calendar.dto.WorkShiftDto;
import com.appzoneltd.lastmile.microservice.calendar.entity.WorkShiftEntity;

public class WorkShiftTransformer {

	public static List<WorkShiftDto> getWorkShiftDtosFromEntities(List<WorkShiftEntity> workShiftEntities) {
		List<WorkShiftDto> workShiftDtos = new ArrayList<>();
		if(workShiftEntities !=null){
			for(WorkShiftEntity workShiftEntity:workShiftEntities){
				workShiftDtos.add(getWorkShiftDtoFromEntity(workShiftEntity));
			}
		}
		return workShiftDtos;
	}

	public static WorkShiftDto getWorkShiftDtoFromEntity(WorkShiftEntity workShiftEntity) {
		if(workShiftEntity==null){
			return null;
		}		
		WorkShiftDto workShiftDto = new WorkShiftDto();
		workShiftDto.setId(workShiftEntity.getId());
		workShiftDto.setFrom(workShiftEntity.getDateFrom());
		workShiftDto.setTo(workShiftEntity.getDateTo());
		workShiftDto.setVersion(workShiftEntity.getVersion());
		if(workShiftEntity.getBuilding() !=null){
			workShiftDto.setHubId(workShiftEntity.getBuilding().getBuildingId());
		}
		return workShiftDto;
	}

}
