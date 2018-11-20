package com.appzoneltd.lastmile.microservice.vehicle.controller;

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

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.vehicle.dao.Vehicle;
import com.appzoneltd.lastmile.microservice.vehicle.dao.VehicleInfo;
import com.appzoneltd.lastmile.microservice.vehicle.model.Parameter;
import com.appzoneltd.lastmile.microservice.vehicle.service.VehicleService;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private MessageSource messageSource;

	@PreAuthorize("hasRole('ROLE_listvehicle')")
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> vehicleFindAll(@RequestBody Parameter requestparameters) {
		List<VehicleInfo> vehicles = new ArrayList<>();
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		PageRequest pageRequest =null;
		if(requestparameters.getPage()>0){
			pageRequest = new PageRequest(requestparameters.getPage() - 1, requestparameters.getMaxResult(),
					Direction.fromString(requestparameters.getOrderBy().toString()), "created");
			
		}
		vehicles = vehicleService.getAllVehiclesInfo(principal, pageRequest);
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_listvehicle')")
	@RequestMapping(value = "/findallforhub", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> vehicleFindAllForHub(@RequestBody Parameter requestparameters) {
		List<VehicleInfo> vehicles = new ArrayList<>();
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		PageRequest pageRequest =null;
		if(requestparameters.getPage()>0){
			pageRequest = new PageRequest(requestparameters.getPage() - 1, requestparameters.getMaxResult(),
					Direction.fromString(requestparameters.getOrderBy().toString()), "created");
			
		}
		vehicles = vehicleService.getAllVehiclesInfoForHub(principal, pageRequest , requestparameters.getHubId());
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}

	 @PreAuthorize("hasRole('ROLE_listvehicle')")
	@RequestMapping(value = "/findallcount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> vehicleFindAll() {
		List<VehicleInfo> vehicles = new ArrayList<>();
		PageRequest pageRequest = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		vehicles = vehicleService.getAllVehiclesInfo(principal, pageRequest);
		return new ResponseEntity<Object>(
				new Message(MessageType.SUCCESS, Integer.toString(vehicles.size()), messageSource.getMessage(
						"vehicle.countall.success", null, "vehicle.countall.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/findbyid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehicleInfo> findById(@RequestBody EndPointParameter requestparameters)
			throws EntityNotFoundException {
		VehicleInfo vehicle = null;
		vehicle = vehicleService.findVehicleById(requestparameters.getId());
		if (vehicle == null)
			throw new EntityNotFoundException("vehicle.vehicleid.exist");
		return new ResponseEntity<VehicleInfo>(vehicle, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_addeditvehicle')")
	@RequestMapping(value = "/createvehicle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createVehicle(@Validated @RequestBody Vehicle vehicle) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<Message> messages = vehicleService.saveVehicle(vehicle, principal);
		if (messages == null) {
			return new ResponseEntity<>(
					new Message(MessageType.SUCCESS, "vehicle", messageSource.getMessage("vehicle.create.success", null,
							"vehicle.create.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		} else
			return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasRole('ROLE_deletevehicle')")
	@RequestMapping(value = "/activeatedeativatevehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> removeVehicle(@RequestBody Vehicle vehicle) throws EntityNotFoundException {
		boolean result = vehicleService.removeVehicle(vehicle.getVehicleId(),vehicle.getStatus().getValue());
		if (!result)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "vehicleId", messageSource.getMessage(
							"vehicle.remove.error", null, "vehicle.remove.error", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, "vehicleId", messageSource.getMessage(
						"vehicle.remove.success", null, "vehicle.remove.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicleWithActiveVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> checkVehicleWithActiveVehicle(@RequestBody EndPointParameter requestparameters) throws EntityNotFoundException {
		boolean result = vehicleService.checkVehicleRelatedToActiveVehicle(requestparameters.getId());
		if (result)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, requestparameters.getId().toString(), messageSource.getMessage(
							"vehicle.related.activevehicle", null, "vehicle.related.activevehicle", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, requestparameters.getId().toString(), messageSource.getMessage(
						"vehicle.remove.success", null, "vehicle.remove.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}
}
