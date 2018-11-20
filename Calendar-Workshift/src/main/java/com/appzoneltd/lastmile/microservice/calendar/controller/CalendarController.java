package com.appzoneltd.lastmile.microservice.calendar.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.calendar.dto.CalendarDto;
import com.appzoneltd.lastmile.microservice.calendar.model.Parameter;
import com.appzoneltd.lastmile.microservice.calendar.service.HubCalendarService;

@RestController
public class CalendarController {

	@Autowired
	private HubCalendarService hubCalendarService;

	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCalendars(@RequestBody Parameter parameter) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<CalendarDto> calendars = hubCalendarService.findAllHubCalendars(parameter.getHubId(), principal);
		return new ResponseEntity<>(calendars, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCalendars(@RequestBody CalendarDto calendarDto) {
		calendarDto = hubCalendarService.updateCalendarStatus(calendarDto);
		if (calendarDto == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(calendarDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/findone", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findCalendarInfo(@RequestBody CalendarDto calendarDto) {
		if (calendarDto.getId() != null) {
			calendarDto = hubCalendarService.findCalendarDtoWithHubId(calendarDto.getId(), calendarDto.getHubId());
			if (calendarDto == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(calendarDto, HttpStatus.OK);
	}
}
