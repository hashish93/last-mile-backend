package com.appzoneltd.lastmile.microservice.distributionplan.controller;

import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.distributionplan.dto.Plan;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.PlanDetailsDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.TimedPlanDetailsDto;
import com.appzoneltd.lastmile.microservice.distributionplan.service.PlanOrderService;
import com.appzoneltd.lastmile.microservice.distributionplan.service.PlanService;

/**
 * Created by Ahmed.Atef on 03/07/2017.
 */
@RestController
public class PlanController {
	private final PlanService planService;

	@Autowired
	public PlanOrderService planOrderService;

	@Autowired
	public PlanController(PlanService planService) {
		this.planService = planService;

	}

	@RequestMapping(value = "/viewPlans", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Plan>> getPlans() {

		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		return new ResponseEntity<>(planService.getPlans(principal), OK);
	}

	@RequestMapping(value = "/viewPlanDetailsForToday", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPlanDetails() {

		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<PlanDetailsDto> planDetailsDtos = planService.planDetails(principal);
		return new ResponseEntity<>(planDetailsDtos, OK);
	}

	@RequestMapping(value = "/getTempAutomaticPlan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTempAutomaticPlan() {

		TimedPlanDetailsDto timedPlanDetailsDto = planService.tmpPlanDetails();
		return new ResponseEntity<>(timedPlanDetailsDto, OK);
	}

}
