package com.appzoneltd.lastmile.microservice.calendar.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appzoneltd.lastmile.microservice.calendar.dao.HubCalendarDao;
import com.appzoneltd.lastmile.microservice.calendar.dto.CalendarDto;
import com.appzoneltd.lastmile.microservice.calendar.entity.HubCalendarEntity;
import com.appzoneltd.lastmile.microservice.calendar.transformer.HubCalendarTransformer;

@Service
public class HubCalendarService {

	@Autowired
	private HubCalendarDao hubCalendarDao;

	@Autowired
	private PrincipalService principalService;

	public List<CalendarDto> findAllHubCalendars(Long hubId, Principal principal) {
		List<CalendarDto> calendarDtos = new ArrayList<>();
		if (principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)) {
			if (hubId != null) {
				List<HubCalendarEntity> hubCalendarEntities = hubCalendarDao.findAllHubCalendar(hubId);
				calendarDtos.addAll(HubCalendarTransformer.getCalendarDtosFromEntities(hubCalendarEntities));
			}
		} else {
			 hubId = principalService.getHubIdIfFound(principal);
			if (hubId != null) {
				List<HubCalendarEntity> hubCalendarEntities = hubCalendarDao.findAllHubCalendar(hubId);
				calendarDtos.addAll(HubCalendarTransformer.getCalendarDtosFromEntities(hubCalendarEntities));
			}
		}
		return calendarDtos;
	}

	public CalendarDto updateCalendarStatus(CalendarDto calendarDto) {
		HubCalendarEntity hubCalendarEntity = null;
		if (calendarDto.getId() != null) {
			hubCalendarEntity = findHubCalendar(calendarDto.getId(), calendarDto.getHubId());
			if (hubCalendarEntity != null) {
				hubCalendarEntity.setStatus(calendarDto.getStatus());
				hubCalendarDao.save(hubCalendarEntity);
				return HubCalendarTransformer.getCalendarDtoFromEntity(hubCalendarEntity);
			}
		}
		return null;
	}

	public HubCalendarEntity findHubCalendar(Long calendarId, Long hubId) {
		return hubCalendarDao.findHubCalendarwithCalendarId(calendarId, hubId);
	}

	public CalendarDto findCalendarDtoWithHubId(Long calendarId, Long hubId) {
		return HubCalendarTransformer.getCalendarDtoFromEntity(findHubCalendar(calendarId, hubId));
	}

}
