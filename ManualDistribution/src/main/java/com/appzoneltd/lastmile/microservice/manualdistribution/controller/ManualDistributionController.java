package com.appzoneltd.lastmile.microservice.manualdistribution.controller;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderTodayDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.MyParameter;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.ValidatePlanRequest;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.VehiclePlanDetails;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.ManualDistributionServiceImpl;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.PrincipalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ManualDistributionController {

    @Autowired
    private ManualDistributionServiceImpl manualDistributionServiceImpl;
    
    @Autowired
    private PrincipalService principalService;

    @RequestMapping(value = "/validateManualDistributionPlan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateManualDistributionPlan(@RequestBody ValidatePlanRequest validatePlanRequest)
            throws Exception {

        List<VehiclePlanDetails> data = manualDistributionServiceImpl
                .buildPlanDetails(validatePlanRequest.getPlanOutlines());

        return new ResponseEntity<>(data, org.springframework.http.HttpStatus.OK);

    }

//    @RequestMapping(value = "/checkOrdersToActiveVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> checkOrdersToActiveVehicle(@RequestBody ValidatePlanRequest validatePlanRequest)
//            throws Exception {
//
//        boolean data = manualDistributionServiceImpl.checkOrdersToActiveVehicle();
//        return new ResponseEntity<>(data, org.springframework.http.HttpStatus.OK);
//
//    }

    @RequestMapping(value = "/getAllOrderForToday", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateManualDistributionPlan(@RequestBody MyParameter myParameter) throws Exception {
 
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		Long hubForUser = null;
		if(principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)){
			hubForUser = myParameter.getHubId();
		}else{
			hubForUser = principalService.getHubIdIfFound(principal);
		}
        List<JobOrderTodayDto> data = new ArrayList<>();
        if(hubForUser !=null){
        	data = manualDistributionServiceImpl.getJobOrderForToday(hubForUser);
        }
       
        return new ResponseEntity<>(data, org.springframework.http.HttpStatus.OK);

    }

}
