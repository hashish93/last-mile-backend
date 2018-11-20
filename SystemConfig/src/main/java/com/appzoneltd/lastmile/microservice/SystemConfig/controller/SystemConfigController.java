package com.appzoneltd.lastmile.microservice.SystemConfig.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.SystemConfig.dao.SystemConfig;
import com.appzoneltd.lastmile.microservice.SystemConfig.service.SystemConfigService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
public class SystemConfigController {
	private final SystemConfigService systemConfigService;
	private final MessageSource messageSource;

	@Autowired
	public SystemConfigController(SystemConfigService systemConfigService, MessageSource messageSource) {
		this.systemConfigService = systemConfigService;
		this.messageSource = messageSource;
	}

//	@PreAuthorize("hasRole('ROLE_editconfig')")
	@RequestMapping(value = "/editconfiguration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updateSystemConfig(@Validated @RequestBody SystemConfig systemConfig)
			throws EntityNotFoundException, JsonProcessingException {
		int result = 0;
		result = systemConfigService.updateSystemConfig(systemConfig);


		return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, Integer.toString(result),
				messageSource.getMessage("systemconfig.update.success", null, "systemconfig.update.success",
						LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('ROLE_config')")
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SystemConfig>> viewAllConfig() {
		List<SystemConfig> systemConfigs = null;
		systemConfigs = systemConfigService.findallSystemConfig();
		if (systemConfigs == null)
			return new ResponseEntity<List<SystemConfig>>(systemConfigs, HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(systemConfigs, HttpStatus.OK);
	}

	@RequestMapping(value = "/countall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> countAll() {
		return new ResponseEntity<Object>(
				new Message(MessageType.SUCCESS, Integer.toString(systemConfigService.countAllSystemConfig()),
						messageSource.getMessage("SystemConfig.countall.success", null, "SystemConfig.countall.success",
								LocaleContextHolder.getLocale())),
				(HttpStatus.OK));
	}

	@RequestMapping(value = "/findbyid", method = RequestMethod.POST)
	public ResponseEntity<SystemConfig> viewConfigByKey(@RequestBody EndPointParameter endPointParameter) {
		SystemConfig SystemConfigData = null;
		SystemConfigData = systemConfigService.findConfigById(endPointParameter.getId());

		if (SystemConfigData == null)
			return new ResponseEntity<SystemConfig>(SystemConfigData, HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(SystemConfigData, HttpStatus.OK);
	}

}
