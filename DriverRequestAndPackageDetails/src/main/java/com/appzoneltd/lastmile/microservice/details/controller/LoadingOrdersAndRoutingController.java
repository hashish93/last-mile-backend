package com.appzoneltd.lastmile.microservice.details.controller;

import com.appzoneltd.lastmile.microservice.details.dto.BaseRequestParameter;
import com.appzoneltd.lastmile.microservice.details.dto.DriverResponseDto;
import com.appzoneltd.lastmile.microservice.details.dto.TodaySummary;
import com.appzoneltd.lastmile.microservice.details.service.LoadingOrdersService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alaa.nabil on 3/23/2017.
 */
@RestController
public class LoadingOrdersAndRoutingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final LoadingOrdersService loadingOrdersService;
    private final MessageSource messageSource;

    @Autowired
    public LoadingOrdersAndRoutingController(LoadingOrdersService loadingOrdersService, MessageSource messageSource) {
        this.loadingOrdersService = loadingOrdersService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/loadingActivitiesForToday", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLoadingActivitiesForToday() {
        try {
            return new ResponseEntity<>(loadingOrdersService.getLoadingActivitiesForToday(SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity<>(new Message(MessageType.ERROR, messageSource.getMessage(e.getMessage(), null, e.getMessage(), LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/loadingActivityResponse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadingActivityResponse(@RequestBody DriverResponseDto driverResponseDto) {
        try {
            return new ResponseEntity<>(loadingOrdersService.responseLoadingOrder(driverResponseDto, SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new Message(MessageType.ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getJobOrdersRoute", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoute() {
        try {
            return new ResponseEntity<>(loadingOrdersService.getJobOrdersRoute(SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new Message(MessageType.ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getJobOrders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listJobOrders() {
        try {
            return new ResponseEntity<>(loadingOrdersService.getJobOrders(SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity<>(new Message(MessageType.ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getJobOrderDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobOrderDetails(@RequestBody BaseRequestParameter baseRequestParameter) throws EntityNotFoundException {
        return new ResponseEntity<>(loadingOrdersService.getJobOrderDetails(baseRequestParameter.getRequestId(), baseRequestParameter.getRequestType()), HttpStatus.OK);
    }

    @RequestMapping(value = "/todaySummary", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodaySummary> todaySummary() throws EntityNotFoundException {
        return new ResponseEntity<>(loadingOrdersService.calculateTodaySummary(SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
    }


    @RequestMapping(value = "/startworking", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> startWorkingToActiveVehicle() throws EntityNotFoundException {

        loadingOrdersService.startWorkingToActiveVehicle(SecurityContextHolder.getContext().getAuthentication());

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
