package com.appzoneltd.lastmile.microservice.calendar.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.calendar.dto.WorkShiftDto;
import com.appzoneltd.lastmile.microservice.calendar.model.Parameter;
import com.appzoneltd.lastmile.microservice.calendar.service.WorkShiftService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;


/**
 * @author Khaled & Hashish
 *
 */
@RestController
@RequestMapping("/workshift")
public class WorkShiftController {

	@Autowired
	private WorkShiftService workShiftService;
	
	@Autowired
	private MessageSource messageSource ;

	@PreAuthorize("hasRole('ROLE_viewcalendar')")
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllWorkShift(@RequestBody Parameter parameter) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<WorkShiftDto> workShiftDtos = workShiftService.findAllWorkShifts(parameter.getHubId() , principal);
		return new ResponseEntity<>(workShiftDtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_editcalendar')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashSet<Long>> addShifts(@RequestBody List<WorkShiftDto> workShiftDtos) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		HashSet<Long> result = workShiftService.savingValidAddedShifts(workShiftDtos,principal);
		if (result.isEmpty()){
			return new ResponseEntity<HashSet<Long>>(result, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<HashSet<Long>>(result, HttpStatus.CONFLICT);
		}
	}
	
	
	
	@RequestMapping(value = "/shiftwithactivevehicle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> checkShiftWithActiveVehicle(@RequestBody WorkShiftDto workShiftDto) {

		boolean result = workShiftService.checkActiveVehicleWithinWorkShift(workShiftDto.getId());

		if (result) {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, workShiftDto.getId().toString(), messageSource.getMessage(
							"workshift.related.activevehicle", null, "workshift.related.activevehicle", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, workShiftDto.getId().toString(), messageSource.getMessage(
							"workshift.remove.success", null, "workshift.remove.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}

	}

	@PreAuthorize("hasRole('ROLE_editcalendar')")
	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> deleteWorkShifts(@RequestBody WorkShiftDto WorkShiftDto ) throws EntityNotFoundException {
		Long result = workShiftService.deleteWorkShift(WorkShiftDto.getId());
		
		if (result==null){
			return new ResponseEntity<Long>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else{
			return new ResponseEntity<Long>(result, HttpStatus.OK);
		}
	}
	
	
	
}
