package com.appzoneltd.lastmile.microservice.packge.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.packge.dto.VerifiedPackageDTO;
import com.appzoneltd.lastmile.microservice.packge.dto.PackageDTO;
import com.appzoneltd.lastmile.microservice.packge.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.packge.model.Parameter;
import com.appzoneltd.lastmile.microservice.packge.service.PackageService;
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

import java.util.Map;

@RestController
public class PackageController {

	
	@Autowired
	private PackageService packageService;
	@Autowired
	private  MessageSource messageSource;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createpackage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPackage(@Validated @RequestBody PackageDTO packageDTO) {
		PackageEntity packageEntity= packageService.saveOrUpdatePackage(packageDTO);
		if (packageEntity == null)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, null,
							messageSource.getMessage("package.create.error", null, "package.create.error", LocaleContextHolder.getLocale())),
					HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, Long.toString(packageEntity.getPackageId()),
						messageSource.getMessage("package.create.success", null, "package.create.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}


	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updatepackage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePackage(@Validated @RequestBody PackageDTO packageDTO)
			throws EntityNotFoundException {
		PackageEntity packageEntity= packageService.saveOrUpdatePackage(packageDTO);
		if (packageEntity == null)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, Long.toString(packageEntity.getPackageId()),
							messageSource.getMessage("package.update.error", null, "package.update.error", LocaleContextHolder.getLocale())),
					HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, Long.toString(packageEntity.getPackageId()),
						messageSource.getMessage("package.update.success", null, "package.update.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}


	@RequestMapping(value = "/findbyid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@RequestBody EndPointParameter requestparameters)
			throws EntityNotFoundException {
		PackageDTO packageDTO = packageService.getPackageById(requestparameters.getId());
		if(packageDTO != null)
			return new ResponseEntity<PackageDTO>(packageDTO, HttpStatus.OK);
		else
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "packageId",
							messageSource.getMessage("package.find.error", null, "package.find.error", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updatepackagestatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePackageStatus(@RequestBody Parameter parameter)
			throws EntityNotFoundException {
		boolean result = packageService.updatePackageStatus(parameter.getId(), parameter.getStatus());
		if (!result)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "packageId",
							messageSource.getMessage("package.update.error", null, "package.update.error", LocaleContextHolder.getLocale())),
					HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Message>(
				new Message(MessageType.SUCCESS, "packageId",
						messageSource.getMessage("package.update.success", null, "package.update.success", LocaleContextHolder.getLocale())),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/verifiedPackage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getVerifiedPackage(@RequestBody EndPointParameter requestparameters)
			throws EntityNotFoundException {
		VerifiedPackageDTO verifiedPackageDTO = packageService.findVerifiedPackageById(requestparameters.getId());
		return new ResponseEntity<>(verifiedPackageDTO, HttpStatus.OK);
	}
}
