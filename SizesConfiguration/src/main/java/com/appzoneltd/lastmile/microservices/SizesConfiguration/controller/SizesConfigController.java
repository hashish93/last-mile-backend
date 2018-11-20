package com.appzoneltd.lastmile.microservices.SizesConfiguration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.DTO.SizesConfigDto;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.model.SizesConfig;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.service.SizesConfigService;

@RestController
public class SizesConfigController {

	@Autowired
	private SizesConfigService sizeConfigService;

	@Autowired
	private MessageSource messageSource;

	@PreAuthorize("hasRole('ROLE_config')")
	@RequestMapping(value = "/findall", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SizesConfigDto>> getAllSizesConfig() {
		List<SizesConfigDto> AllSizes = null;

		AllSizes = sizeConfigService.findAllSizesConfig();

		return new ResponseEntity<List<SizesConfigDto>>(AllSizes, HttpStatus.OK);

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSizesConfig(@Validated @RequestBody SizesConfigDto sizesConfigDto) {
		List<Message> messages = sizeConfigService.saveSizesConfig(sizesConfigDto);
		if (messages != null) {

			return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Message>(
					new Message(MessageType.SUCCESS, "created", messageSource.getMessage("sizesConfig.create.success",
							null, "sizesConfig.create.success", LocaleContextHolder.getLocale())),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSizesConfig(@Validated @RequestBody SizesConfigDto sizesConfigDto) {
		try {
			List<Message> messages = sizeConfigService.updateSizesConfig(sizesConfigDto);
			if (messages != null) {

				return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "updated",
						messageSource.getMessage("sizesConfig.update.success", null, "sizesConfig.update.success",
								LocaleContextHolder.getLocale())),
						HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<Message>(
					new Message(MessageType.WARNING,
							"updated", messageSource.getMessage("sizesConfig.update.warning", null,
									"sizesConfig.update.warning", LocaleContextHolder.getLocale())),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/findbyid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findSizesConfigById(@RequestBody SizesConfigDto sizesConfigDto) {

		SizesConfigDto sizeConfigInfo = sizeConfigService.findbyId(sizesConfigDto.getSizeId());

		if (sizeConfigInfo != null) {

			return new ResponseEntity<>(sizeConfigInfo, HttpStatus.OK);
		} else {

			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "error", " not found"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteSizesConfigById(@RequestBody SizesConfigDto sizesConfigDto) {

		SizesConfig deactivateConfig = sizeConfigService.deleteSizesConfig(sizesConfigDto);

		if (deactivateConfig != null) {
			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "Success", " Done"),
					HttpStatus.OK);
		}

		else {

			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "error", " can't be deleted"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
