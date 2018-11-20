package com.appzoneltd.lastmile.microservice.hubconfig.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.hubconfig.dto.HubCofigurationDto;
import com.appzoneltd.lastmile.microservice.hubconfig.model.Parameter;
import com.appzoneltd.lastmile.microservice.hubconfig.service.HubConfigurationService;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
@RequestMapping("/hubConfig")
public class HubConfigurationController {

	@Autowired
	private HubConfigurationService hubConfigurationService;

	@Autowired
	private MessageSource messageSource;
	
//	@PreAuthorize("hasRole('ROLE_editconfig')")
	@RequestMapping(value = "/editconfiguration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updateSystemConfig(@Validated @RequestBody HubCofigurationDto hubCofigurationDto)
			throws EntityNotFoundException {		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		boolean updated= hubConfigurationService.updateHubConfiguration(hubCofigurationDto, principal);

		if(!updated){
			throw new EntityNotFoundException("Configuration Not Found");
		}
		return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "ConfigId",
				messageSource.getMessage("systemconfig.update.success", null, "systemconfig.update.success",
						LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}
	
	// @PreAuthorize("hasRole('ROLE_config')")
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewAllConfigurations(@RequestBody Parameter parameter) {			
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<HubCofigurationDto> hubCofigurationDtos = hubConfigurationService
					.getAllConfigurationEntities(parameter.getHubId(),principal,parameter.getConfigType());
			return new ResponseEntity<>(hubCofigurationDtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/countall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> countAll(@RequestBody Parameter parameter) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		int count=hubConfigurationService.countAllConfiguration(parameter.getHubId(), principal,parameter.getConfigType());
		return new ResponseEntity<Object>(
				new Message(MessageType.SUCCESS, ""+count,
						messageSource.getMessage("SystemConfig.countall.success", null, "SystemConfig.countall.success",
								LocaleContextHolder.getLocale())),
				(HttpStatus.OK));
	}

	@RequestMapping(value = "/findbyid", method = RequestMethod.POST)
	public ResponseEntity<?> viewConfigByKey(@RequestBody Parameter Parameter) throws EntityNotFoundException {		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		HubCofigurationDto hubCofigurationDto =  hubConfigurationService.findConfigurationById(Parameter.getHubId(), Parameter.getId(), principal);
		if (hubCofigurationDto == null){
			throw new EntityNotFoundException("Configuration Not Found");
		}
		return new ResponseEntity<>(hubCofigurationDto, HttpStatus.OK);
	}

}
