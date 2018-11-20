//package com.appzoneltd.lastmile.microservice.manualdistribution.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.model.Region;
//import com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.service.OurNewService;
//
//@RestController
//public class OurController {
//
//	@Autowired
//	private OurNewService ourNewService;
//
//
//	@RequestMapping(value = "/test", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> validateManualDistributionPlan()throws Exception {
//
//		List<Region> regions=ourNewService.testNewAlgorithm();
//
//		return new ResponseEntity<>(regions, org.springframework.http.HttpStatus.OK);
//
//	}
//}
