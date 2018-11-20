//package com.appzoneltd.lastmile.microservice.manualdistribution.controller;
//
//import com.appzoneltd.lastmile.microservice.manualdistribution.service.automaticservice.SavePlanTmpToPlan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by hashish on 4/10/2017.
// */
//@RestController
//public class AutomaticDistributionController {
//
//    @Autowired
//    private SavePlanTmpToPlan savePlanTmpToPlan;
//
//    @RequestMapping(value = "/regenerateAutomaticPlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> regenerateAutomaticPlan() throws Exception {
//        Long result = automaticDistributionService.savePlan();
//        if (result == null) {
//            return new ResponseEntity<>("can't generate plane for today", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>(result, org.springframework.http.HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "/saveAutomaticPlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveAutomaticPlan() throws Exception {
//        Long id = savePlanTmpToPlan.save();
//        if (id != null) {
//            return new ResponseEntity<>(id, org.springframework.http.HttpStatus.OK);
//        }
//        return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
