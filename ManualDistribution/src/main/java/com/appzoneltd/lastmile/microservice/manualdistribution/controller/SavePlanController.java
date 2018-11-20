package com.appzoneltd.lastmile.microservice.manualdistribution.controller;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.PlanOrdersHubContainer;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.MyParameter;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.TodayPlan;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.PrincipalService;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.saveplan.SavePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SavePlanController {

    private final SavePlanService savePlanService;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    public SavePlanController(SavePlanService savePlanService) {
        this.savePlanService = savePlanService;
    }

    @RequestMapping(value = "/saveplan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateManualDistributionPlan(@RequestBody @Validated PlanOrdersHubContainer planOrdersHubContainer)
            throws Exception {

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Long hubForUser = null;
        if (principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)) {
            hubForUser = planOrdersHubContainer.getHubId();
        } else {
            hubForUser = principalService.getHubIdIfFound(principal);
        }
        if (hubForUser == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Long result = savePlanService.savePlan(planOrdersHubContainer.getSavePlanOrders(), planOrdersHubContainer.getHubId());
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/checkPlanForToday", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkExistedPlanForToday(@RequestBody MyParameter myParameter) throws Exception {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Long hubForUser = null;
        TodayPlan todayPlan = null;
        if (principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)) {
            hubForUser = myParameter.getHubId();
        } else {
            hubForUser = principalService.getHubIdIfFound(principal);
        }
        if (hubForUser != null) {
            todayPlan = savePlanService.checkPlanForToday(hubForUser);
        }

        return new ResponseEntity<>(todayPlan, HttpStatus.OK);
    }

}
