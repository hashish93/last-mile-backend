package com.appzoneltd.lastmile.microservice.distributionplan.controller;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.distributionplan.dto.ActiveOrderDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.ActiveVehicleOrderDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.RequestParameter;
import com.appzoneltd.lastmile.microservice.distributionplan.service.ActiveOrderService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
public class ActiveOrdersController {

	private ActiveOrderService activeOrderService;

	private final MessageSource messageSource;

	public ActiveOrdersController(ActiveOrderService activeOrderService, MessageSource messageSource) {
		this.activeOrderService = activeOrderService;
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/activeOrderList", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ActiveOrderDto>> getActiveOrdersDetails(@RequestBody RequestParameter requestParameter) {
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<ActiveOrderDto> activeOrderDtos = activeOrderService.getAllActiveOrders(requestParameter.getHubId(),principal);

		return new ResponseEntity<List<ActiveOrderDto>>(activeOrderDtos, OK);
	}

	@RequestMapping(value = "/activeOrderDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ActiveVehicleOrderDto> getActiveOrdersList(@RequestBody RequestParameter requestParameter) {

		ActiveVehicleOrderDto vehicleOrderDto = null;

		if ("SCHEDULED_SERVICES".equalsIgnoreCase(requestParameter.getType())) {

			vehicleOrderDto = activeOrderService.getActionOrderListForScheduledVehicle(requestParameter.getId());

		}

		if ("ON_DEMAND_SERVICES".equalsIgnoreCase(requestParameter.getType())) {

			vehicleOrderDto = activeOrderService.getActionOrderListForOndemandVehicle(requestParameter.getId());
		}

		if (vehicleOrderDto != null) {
			return new ResponseEntity<ActiveVehicleOrderDto>(vehicleOrderDto, OK);
		} else {
			return new ResponseEntity<ActiveVehicleOrderDto>(CONFLICT);
		}

	}

	@RequestMapping(value = "/activeVehicleRelatedToTodayPlan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> chkActiveVehicleRelatedToTodayPlan(@RequestBody RequestParameter requestParameter) {
		boolean result = activeOrderService.chkActiveVehicleRelatedToTodayPlan(requestParameter.getId());
		if (result)
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR,
							requestParameter.getId().toString(), messageSource.getMessage("activevehicle.remove.error",
									null, "activevehicle.remove.error", LocaleContextHolder.getLocale())),
					HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, requestParameter.getId().toString(),
					messageSource.getMessage("activevehicle.remove.success", null, "activevehicle.remove.success",
							LocaleContextHolder.getLocale())),
					HttpStatus.OK);

	}

}
