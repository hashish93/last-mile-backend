package com.appzoneltd.lastmile.microservice.pickuprequest.controller;

import com.appzoneltd.lastmile.microservice.pickuprequest.dto.CustomerRequestDetails;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.DriverInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.CustomerRequestDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
@RestController
public class CustomerRequestsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PickupRequestController.class);

    private final CustomerRequestDetailsService customerRequestDetailsService;

    @Autowired
    public CustomerRequestsController(CustomerRequestDetailsService customerRequestDetailsService) {
        this.customerRequestDetailsService = customerRequestDetailsService;
    }


    @RequestMapping(value = "/customerRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerRequestDetails> customerRequestDetails(@RequestBody HashMap<String, Long> params) {
        CustomerRequestDetails customerRequestDetails = customerRequestDetailsService.getRequestDetailsForCustomer(params.get("id"));
        return new ResponseEntity<>(customerRequestDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "/driverInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> driverInfo(@RequestBody HashMap<String, Long> params) {
        DriverInfo driverInfo = null;
        try {
            driverInfo = customerRequestDetailsService.getDriverInfo(params.get("id"));
        }catch (Exception e){
            new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if (driverInfo == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(driverInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/tracePackage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerRequestDetails> tracePackage(@RequestBody HashMap<String, String> params) {
        CustomerRequestDetails customerRequestDetails = customerRequestDetailsService.tracePackageWithTrackingNumberOrNickname(params.get("identifier"), SecurityContextHolder.getContext().getAuthentication().getName());
        if (customerRequestDetails == null)
            return new ResponseEntity<>(customerRequestDetails, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(customerRequestDetails, HttpStatus.OK);
    }
}
