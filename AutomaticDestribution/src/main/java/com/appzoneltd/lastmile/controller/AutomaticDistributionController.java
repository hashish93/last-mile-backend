//package com.appzoneltd.lastmile.controller;
//
//import com.appzoneltd.lastmile.microservice.manualdistribution.model.VehiclePlanDetails;
//import com.appzoneltd.lastmile.service.AutomaticDistributionService;
//import com.appzoneltd.lastmile.service.SavePlanTmpToPlan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class AutomaticDistributionController {
//
//	@Autowired
//	private AutomaticDistributionService automaticDistributionService;
//	@Autowired
//	private SavePlanTmpToPlan savePlanTmpToPlan;
//
//	@RequestMapping(value = "/saveTmpPlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> saveTmpPlan() throws Exception {
//	Long result = automaticDistributionService.savePlan();
//	if(result==null){
//		return new ResponseEntity<>("can't generate plane for today", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//		return new ResponseEntity<>(result, org.springframework.http.HttpStatus.OK);
//	}
//
//
//
//	@RequestMapping(value = "/savePlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> savePlan() throws Exception {
//		Long id = savePlanTmpToPlan.save();
//		if(id !=null){
//			return new ResponseEntity<>(id, org.springframework.http.HttpStatus.OK);
//		}
//		return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//}
