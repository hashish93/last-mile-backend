package com.appzoneltd.lastmile.microservice.delivery.controller;

import com.appzoneltd.lastmile.microservice.delivery.dto.CustomerDeliveryRequest;
import com.appzoneltd.lastmile.microservice.delivery.dto.CustomerRequestDetails;
import com.appzoneltd.lastmile.microservice.delivery.service.CustomerRequestDetailsService;
import com.appzoneltd.lastmile.microservice.delivery.service.DeliveryService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alaa.nabil on 5/10/2017.
 */
@RestController
public class CustomerDeliveryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDeliveryController.class);

    private final DeliveryService deliveryService;
    private final MessageSource messageSource;

    @Autowired
    public CustomerDeliveryController(DeliveryService deliveryService, MessageSource messageSource) {
        this.deliveryService = deliveryService;
        this.messageSource = messageSource;
    }
    
    @Autowired
    private CustomerRequestDetailsService customerRequestDetailsService;
    
    @RequestMapping(value = "/customerRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerRequestDetails> customerRequestDetails(@RequestBody HashMap<String, Long> params) {
        CustomerRequestDetails customerRequestDetails = customerRequestDetailsService.getRequestDetailsForCustomer(params.get("id"));
        return new ResponseEntity<>(customerRequestDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "/myDeliveries", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDeliveryRequest>> findRequestByUserId() {
        List<CustomerDeliveryRequest> customerDeliveryRequests = null;
        try {
            customerDeliveryRequests = deliveryService.getRequestForUser(SecurityContextHolder.getContext().getAuthentication());
            if (customerDeliveryRequests == null)
                return new ResponseEntity<>(customerDeliveryRequests, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(customerDeliveryRequests, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerDeliveryRequests, HttpStatus.OK);
    }


    @RequestMapping(value = "/isTrackedRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> checkRequestAvailability(@RequestBody EndPointParameter requestparameters) {
        Boolean isTracked = deliveryService.checkTrackedPickupRequest(requestparameters.getId());
        Message message = new Message(MessageType.ERROR, isTracked.toString());
        if (isTracked)
            message.setMessageType(MessageType.SUCCESS);

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

}
