package com.appzoneltd.lastmile.microservice.calendar.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.calendar.dto.CalendarDto;
import com.appzoneltd.lastmile.microservice.calendar.entity.HubCalendarEntity;

public class HubCalendarTransformer {

	public static List<CalendarDto> getCalendarDtosFromEntities(List<HubCalendarEntity> hubCalendarEntities) {
		List<CalendarDto> calendarDtos = new ArrayList<>();
		if (hubCalendarEntities != null) {
			for (HubCalendarEntity hubCalendarEntity : hubCalendarEntities) {
				calendarDtos.add(getCalendarDtoFromEntity(hubCalendarEntity));
			}
		}
		return calendarDtos;
	}

	public static CalendarDto getCalendarDtoFromEntity(HubCalendarEntity hubCalendarEntity) {
		CalendarDto calendarDto = new CalendarDto();

		if (hubCalendarEntity.getCalendar() != null) {
			calendarDto.setId(hubCalendarEntity.getCalendar().getId());
			calendarDto.setDayName(hubCalendarEntity.getCalendar().getDayName());
		}

		if (hubCalendarEntity.getBuilding() != null) {
			calendarDto.setHubId(hubCalendarEntity.getBuilding().getBuildingId());
		}

		calendarDto.setStatus(hubCalendarEntity.getStatus());
		calendarDto.setVersion(hubCalendarEntity.getVersion());
		return calendarDto;
	}

}
