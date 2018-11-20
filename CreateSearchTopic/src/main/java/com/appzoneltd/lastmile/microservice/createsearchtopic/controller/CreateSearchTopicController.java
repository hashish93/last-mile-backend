package com.appzoneltd.lastmile.microservice.createsearchtopic.controller;

import com.appzoneltd.lastmile.microservice.createsearchtopic.dao.CustomerQuery;
import com.appzoneltd.lastmile.microservice.createsearchtopic.dao.QueryDTO;
import com.appzoneltd.lastmile.microservice.createsearchtopic.service.CreateSearchTopicService;
import com.appzoneltd.lastmile.microservice.createsearchtopic.service.CustomerSearchTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alaa.nabil
 */
@RestController
public class CreateSearchTopicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSearchTopicController.class);

    private final CreateSearchTopicService createSearchTopicService;
    private final CustomerSearchTopic customerSearchTopic;

    @Autowired
    public CreateSearchTopicController(CreateSearchTopicService createSearchTopicService,
                                       CustomerSearchTopic customerSearchTopic) {
        this.createSearchTopicService = createSearchTopicService;
        this.customerSearchTopic = customerSearchTopic;
    }

    /**
     * @param queryDTO
     * @return
     */
    // @PreAuthorize("hasRole('ROLE_operationcenter') or
    // hasRole('ROLE_vehicleview')")
    @RequestMapping(value = "/topic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSearchTopic(@RequestBody QueryDTO queryDTO) {
        ResponseEntity<Object> response = null;

        QueryDTO result = null;
        try {
            result = createSearchTopicService.createTopic(queryDTO);
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/customerTopic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSearchTopicForCustomer(@RequestBody CustomerQuery customerQuery) {
        ResponseEntity<Object> response = null;

        CustomerQuery result = null;
        try {
            result = customerSearchTopic.createCustomerTopic(customerQuery);
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

}
