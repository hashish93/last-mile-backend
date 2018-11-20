package com.appzoneltd.lastmile.microservices.devices.controller;

import java.security.Principal;
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
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservices.devices.dto.DeviceDto;
import com.appzoneltd.lastmile.microservices.devices.dto.PageRequestDto;
import com.appzoneltd.lastmile.microservices.devices.entity.DeviceEntity;
import com.appzoneltd.lastmile.microservices.devices.services.DeviceService;

@RestController
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private MessageSource messageSource;

	/*
	 * Service To Get All device in system
	 */

	@PreAuthorize("hasRole('ROLE_listdevices')")
	@RequestMapping(value = "/findall", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeviceDto>> getAllDevices(@RequestBody PageRequestDto pageRequestDto) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<DeviceDto> devices = null;

		// define PageRequest to list
		PageRequest page1 = new PageRequest(pageRequestDto.getPageOffset(), pageRequestDto.getPageSize(),
				Direction.fromString(pageRequestDto.getOrderBy()), "created");

		devices = (List<DeviceDto>) deviceService.findAllDevicesByBage(page1,principal);

		return new ResponseEntity<List<DeviceDto>>(devices, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_listdevices')")
	@RequestMapping(value = "/findallactive", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeviceDto>> getAllActiveDevices() {

		List<DeviceDto> devices = null;
		
		devices = (List<DeviceDto>) deviceService.findallAllActiveDevice();

		return new ResponseEntity<List<DeviceDto>>(devices, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_listdevices')")
	@RequestMapping(value = "/findallactiveForHub", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeviceDto>> getAllActiveDevicesForHub(@RequestBody PageRequestDto pageRequestDto) {

		List<DeviceDto> devices = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		devices = (List<DeviceDto>) deviceService.findallAllActiveDeviceForHub(principal , pageRequestDto.getHubId());

		return new ResponseEntity<List<DeviceDto>>(devices, HttpStatus.OK);
	}
	
	

	/*
	 * Service To Add New device in system
	 */

	@PreAuthorize("hasRole('ROLE_addeditdevices')")
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewDevice(@Validated @RequestBody DeviceDto deviceDto) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<Message> messages = deviceService.saveDevice(deviceDto,principal);

		if (messages != null) {

			return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "created", messageSource.getMessage("device.create.success", null,
							"device.create.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}

	}

	/*
	 * Service To Update device Info in system
	 */

	@PreAuthorize("hasRole('ROLE_addeditdevices')")
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateDeviceInfo(@Validated @RequestBody DeviceDto deviceDto) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<Message> messages = deviceService.updateDevicInfo(deviceDto,principal);

		if (messages != null) {

			return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
		} else {

			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "update", messageSource.getMessage("device.update.success", null,
							"device.update.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}

	}

	/*
	 * Service To Get Information to determine device in system by device Id
	 */

	@RequestMapping(value = "/findbyid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findDevicebyId(@RequestBody DeviceDto deviceDto) {

		DeviceDto deviceInfo = deviceService.findDeviceById(deviceDto);

		if (deviceInfo != null) {

			return new ResponseEntity<>(deviceInfo, HttpStatus.OK);
		} else {

			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "error", " not found"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Service To De-active or Active determine device in system by device Id
	 */

	@PreAuthorize("hasRole('ROLE_deletedevices')")
	@RequestMapping(value = "/activateOrdeactivate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deactivateDevice(@RequestBody DeviceDto deviceDto) throws EntityNotFoundException {

		DeviceEntity device = deviceService.deactivateDevice(deviceDto);

		if (device != null) {

			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "done", "operation done"),
					HttpStatus.OK);
		} else {

			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "error", " can't be activate"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@RequestMapping(value = "/deviceWithActiveVehicle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> checkDeviceWithActiveVehicle(@RequestBody DeviceDto deviceDto) throws EntityNotFoundException {

		boolean result = deviceService.chkDeviceWithActiveVehicle(deviceDto.getDeviceId());

		if (result) {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, deviceDto.getDeviceId().toString(), messageSource.getMessage(
							"device.related.activevehicle", null, "device.related.activevehicle", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, deviceDto.getDeviceId().toString(), messageSource.getMessage(
							"device.remove.success", null, "device.remove.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}

	}

	/*
	 * Service To count all De-active or Active devices in system by device Id
	 */

	@RequestMapping(value = "/countall", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> countDevice() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		Long result = deviceService.countDevice(principal);

		if (result != null) {

			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, result.toString(), "Done"),
					HttpStatus.OK);
		} else {

			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "error", " no devices"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
