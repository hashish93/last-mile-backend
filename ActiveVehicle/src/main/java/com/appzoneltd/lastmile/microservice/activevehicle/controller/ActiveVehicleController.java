package com.appzoneltd.lastmile.microservice.activevehicle.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao.JobOrder;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dto.ActiveVehicle;
import com.appzoneltd.lastmile.microservice.activevehicle.dto.ActiveVehicleInfo;
import com.appzoneltd.lastmile.microservice.activevehicle.model.MyParameter;
import com.appzoneltd.lastmile.microservice.activevehicle.service.ActiveVehicleService;
import com.appzoneltd.lastmile.microservice.activevehicle.service.CouchbaseActiveVehicleOrdersService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
public class ActiveVehicleController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ActiveVehicleController.class);

	private final ActiveVehicleService activeVehicleService;
	private final CouchbaseActiveVehicleOrdersService ordersService;
	private final MessageSource messageSource;

	@Autowired
	public ActiveVehicleController(ActiveVehicleService activeVehicleService, MessageSource messageSource,
			CouchbaseActiveVehicleOrdersService ordersService) {
		this.activeVehicleService = activeVehicleService;
		this.ordersService = ordersService;
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ActiveVehicleInfo>> getActiveVehicles(@RequestBody EndPointParameter endPointParameter) {
		List<ActiveVehicleInfo> activeVehicleEntities = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();

		activeVehicleEntities = activeVehicleService.getActiveVehicles(endPointParameter.getPage(),
				endPointParameter.getMaxResult(), endPointParameter.getOrderBy(), principal);
		if (activeVehicleEntities == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<List<ActiveVehicleInfo>>(activeVehicleEntities, HttpStatus.OK);
	}

	@RequestMapping(value = "/findAllOnDemand", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ActiveVehicleInfo>> getActiveVehiclesOnDemand(
			@RequestBody MyParameter myParameter) {
		List<ActiveVehicleInfo> activeVehicleEntities = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		activeVehicleEntities = activeVehicleService.getActiveVehiclesOnDemand(myParameter.getPage(),
				myParameter.getMaxResult(), myParameter.getOrderBy() , myParameter.getHubId() , principal);
		if (activeVehicleEntities == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<List<ActiveVehicleInfo>>(activeVehicleEntities, HttpStatus.OK);
	}

	@RequestMapping(value = "/findById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ActiveVehicleInfo> getActiveVehicleById(@RequestBody Map<String, Long> param) {
		ActiveVehicleInfo entity = null;
		try {
			entity = activeVehicleService.getActiveVehicle(param.get("id"));
			if (entity == null)
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<ActiveVehicleInfo>(entity, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> createActiveVehicle(@Validated @RequestBody ActiveVehicle activeVehicle) {
		ActiveVehicleEntity vehicleEntity = null;
		try {
			vehicleEntity = activeVehicleService.createActiveVehicle(activeVehicle);
		} catch (DuplicatedKeyException ex) {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, ex.getFieldName(),
							ex.getFieldValue() + " "
									+ messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, vehicleEntity.getId().toString(),
				messageSource.getMessage("activevehicle.add.success", null, "activevehicle.add.success",
						LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updateActiveVehicle(@Validated @RequestBody ActiveVehicle activeVehicle) {
		ActiveVehicleEntity activeVehicleEntity = null;
		activeVehicleEntity = activeVehicleService.updateActiveVehicle(activeVehicle);
		if (activeVehicleEntity == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, activeVehicleEntity.getId().toString(),
				messageSource.getMessage("activevehicle.updated.success", null, "activevehicle.updated.success",
						LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> deleteActiveVehicle(@RequestBody Map<String, Long> param) {
		ResponseEntity<Message> entity = null;
		activeVehicleService.deleteActiveVehicle(param.get("id"));
		entity = new ResponseEntity<Message>(new Message(MessageType.SUCCESS, param.get("id").toString(),
				messageSource.getMessage("activevehicle.deleted.success", null, "activevehicle.deleted.success",
						LocaleContextHolder.getLocale())),
				HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/countAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> countAll() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();

		return new ResponseEntity<Message>(new Message(MessageType.SUCCESS,
				String.valueOf(activeVehicleService.countActiveVehicles(principal)), ""), HttpStatus.OK);
	}

	@RequestMapping(value = "/activevehicleorders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobOrder>> activeVehicleJobOrders(@RequestBody Map<String, Long> param)
			throws EntityNotFoundException {
		return new ResponseEntity<List<JobOrder>>(ordersService.vehicleJobOrders(param.get("id")), HttpStatus.OK);
	}

}
