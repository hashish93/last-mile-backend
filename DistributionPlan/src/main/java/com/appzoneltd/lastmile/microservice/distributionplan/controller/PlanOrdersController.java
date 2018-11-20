package com.appzoneltd.lastmile.microservice.distributionplan.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanInfoRequest;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.PlanOrder;
import com.appzoneltd.lastmile.microservice.distributionplan.model.MyParameter;
import com.appzoneltd.lastmile.microservice.distributionplan.service.PlanOrderService;

/**
 * Created by Ahmed Atef on 2/16/2017.
 */
@RestController
public class PlanOrdersController {
	private final PlanOrderService planOrderService;

	@Autowired
	public PlanOrdersController(PlanOrderService planOrderService) {
		this.planOrderService = planOrderService;
	}

	@RequestMapping(value = "/viewLatestOrders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlanOrder>> getPlans() {
		List<PlanOrder> result = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		result = planOrderService.getOrdersByPlan(principal);
		if (result == null)
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(result, OK);
	}

	@RequestMapping(value = "/viewOrdersByActiveVehicleIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlanOrder>> getPlansByActiveVehilceIds(@RequestBody PlanInfoRequest planInfoRequest) {
		List<PlanOrder> result = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		result = planOrderService.getOrdersByActiveVehicleIds(planInfoRequest.getActiveVehicleIds(), principal);
		if (result == null)
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(result, OK);
	}

	@RequestMapping(value = "/viewActiveVehiclesInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ActiveVehicleDto>> getActiveVehilceInfo(@RequestBody MyParameter myParameter) {

		List<ActiveVehicleDto> result = null;
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		result = planOrderService.findAllActiveVehiclesInfo(principal,myParameter.getHubId());
		if (result == null)
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(result, OK);
	}

}
