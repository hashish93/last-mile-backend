package com.appzoneltd.lastmile.microservice.building.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.ServingArealocation;
import com.appzoneltd.lastmile.microservice.building.dto.CalendarDto;
import com.appzoneltd.lastmile.microservice.building.model.Building;
import com.appzoneltd.lastmile.microservice.building.model.BuildingInfo;
import com.appzoneltd.lastmile.microservice.building.parameter.Parameter;
import com.appzoneltd.lastmile.microservice.building.service.BuildingService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private MessageSource messageSource;

	@PreAuthorize("hasRole('ROLE_listbuilding')")
	@RequestMapping(value = "/findall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingInfo>> viewAllBuildingByPage(@RequestBody Parameter parameter) {

		List<BuildingInfo> vehicles = new ArrayList<>();
		PageRequest pageRequest = null;
		if(parameter.getPage() == 0){
			pageRequest = null;
		}else{
			pageRequest = new PageRequest(parameter.getPage() - 1, parameter.getMaxResult(),
					Direction.fromString(parameter.getOrderBy().toString()), "created");
		}
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		vehicles = buildingService.findAllBuildings(pageRequest, principal);
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}

	@RequestMapping(value = "/countall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> countAllBuilding() {
		List<BuildingInfo> vehicles = new ArrayList<>();
		PageRequest pageRequest = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		vehicles = buildingService.findAllBuildings(pageRequest, principal);
		return new ResponseEntity<Object>(new Message(MessageType.SUCCESS, Integer.toString(vehicles.size()),
				messageSource.getMessage("building.countall.success", null, "building.countall.success",
						LocaleContextHolder.getLocale())),
				(HttpStatus.OK));

	}

	@PreAuthorize("hasRole('ROLE_addeditbuilding')")
	@RequestMapping(value = "/createbuilding", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addBuilding(@Validated @RequestBody Building building) throws DuplicatedKeyException {
		List<Message> messages = buildingService.saveBuilding(building);
		if (messages == null || messages.size() == 0)
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "buildingId", messageSource.getMessage("building.create.success",
							null, "building.create.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		else
			return new ResponseEntity<>(messages, HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/getcalendarforlocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getcalendarforlocation(@RequestBody ServingArealocation point) throws DuplicatedKeyException {
		List<CalendarDto> calendarDtos  = buildingService.getCalendarDtoFromPointLocation(point);
		if (calendarDtos == null) {	
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "range",
							messageSource.getMessage("building.pointlocation.range",
							null, LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		}
		else
			return new ResponseEntity<>(calendarDtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbyid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewBuildingById(@RequestBody Parameter parameter) throws EntityNotFoundException {
		if (parameter.getId() == null) {
			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "buildingId", messageSource
					.getMessage("building.not.found", null, "building.not.found", LocaleContextHolder.getLocale())),
					HttpStatus.BAD_REQUEST);
		}
		BuildingInfo building = null;
		building = buildingService.findBuildingInfoById(parameter.getId());
		if (building == null) {
			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "buildingId", messageSource
					.getMessage("building.not.found", null, "building.not.found", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(building, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_deletebuilding')")
	@RequestMapping(value = "/deletebuilding", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> removeBuilding(@RequestBody Parameter parameter) {
		if (parameter.getId() == null) {
			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "buildingId", messageSource
					.getMessage("building.not.found", null, "building.not.found", LocaleContextHolder.getLocale())),
					HttpStatus.BAD_REQUEST);
		}
		boolean result = buildingService.deactivateBuilding(parameter.getId(), parameter.getStatus());
		if (!result)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "buildingId", messageSource.getMessage("building.remove.error", null,
							"building.remove.error", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "buildingId", messageSource.getMessage("building.remove.success",
							null, "building.remove.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_deletebuilding')")
	@RequestMapping(value = "/vehiclewithbuilding", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> chkVehicleRelatedToBuilding(@RequestBody Parameter parameter) {
		if (parameter.getId() == null) {
			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "buildingId", messageSource
					.getMessage("building.not.found", null, "building.not.found", LocaleContextHolder.getLocale())),
					HttpStatus.BAD_REQUEST);
		}
		boolean result = buildingService.checkVehicleRelatedToBuildingOrUser(parameter.getId());
		if (result)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, parameter.getId().toString(), messageSource.getMessage(
							"building.remove.error", null, "building.remove.error", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, parameter.getId().toString(),
					messageSource.getMessage("building.remove.success", null, "building.remove.success",
							LocaleContextHolder.getLocale())),
					HttpStatus.OK);
	}
}
