package com.appzoneltd.lastmile.microservice.pickuprequest.controller;

import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.OnDemandDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.OnDemandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OnDemandController {

    private static final Logger log = LoggerFactory.getLogger(OnDemandController.class);

    private final OnDemandService onDemandService;

    @Autowired
    public OnDemandController(OnDemandService onDemandService) {
        this.onDemandService = onDemandService;
    }

    @PreAuthorize("hasRole('ROLE_listondemand')")
    @RequestMapping(method = RequestMethod.POST, value = "/getalltakenondemand", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTakenOnDemand(@RequestBody EndPointParameter serviceParameters) {

        ResponseEntity<List<OnDemandDto>> response = null;
        List<OnDemandDto> onDemandRequests = null;
        try {
            onDemandRequests = onDemandService.findTakenOnDemand(serviceParameters.getOrderBy());
            response = new ResponseEntity<>(onDemandRequests, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PreAuthorize("hasRole('ROLE_listondemand')")
    @RequestMapping(method = RequestMethod.POST, value = "/getalluntakenondemand", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUntakenOnDemand(@RequestBody EndPointParameter serviceParameters) {

        ResponseEntity<List<OnDemandDto>> response = null;
        List<OnDemandDto> onDemandRequests = null;
        try {
            onDemandRequests = onDemandService.findUntakenOnDemand(serviceParameters.getOrderBy());
            response = new ResponseEntity<>(onDemandRequests, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/countallondemandrequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> countAllOnDemandRequest() {
        int count = onDemandService.count();
        ResponseEntity<Integer> response = new ResponseEntity<>(count, HttpStatus.OK);
        return response;

    }

}
