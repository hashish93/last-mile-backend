package com.appzoneltd.lastmile.microservice.pickuprequest.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequestInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.RequestParameters;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.ScheduledServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class ScheduledController {

    private final ScheduledServiceImp scheduledServiceImp;
    private final MessageSource messageSource;

    @Autowired
    public ScheduledController(ScheduledServiceImp scheduledServiceImp, MessageSource messageSource) {
        this.scheduledServiceImp = scheduledServiceImp;
        this.messageSource = messageSource;
    }

//    @Auditable(action = Action.EDIT , screenName = Screen.SCHEDULE)
    @RequestMapping(value = "/editScheduledRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> updateScheduledRequest(@RequestBody PickupRequest scheduledRequest)
            throws DuplicatedKeyException, ParseException, EntityNotFoundException {
        int result = 0;

        result = scheduledServiceImp.updateScheduledRequest(scheduledRequest);
        if (result == 0)
            return new ResponseEntity<Message>(
                    new Message(MessageType.ERROR, Integer.toString(result),
                            messageSource.getMessage("scheduledRequest.update.error", null,
                                    "scheduledRequest.update.error", LocaleContextHolder.getLocale())),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, Integer.toString(result),
                messageSource.getMessage("scheduledRequest.update.success", null, "scheduledRequest.update.success",
                        LocaleContextHolder.getLocale())),
                HttpStatus.OK);

    }

//    @Auditable(action = Action.DELETE , screenName = Screen.SCHEDULE)
    @PreAuthorize("hasRole('ROLE_deleteschedule')")
    @RequestMapping(value = "/cancelScheduledRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> cancelScheduledRequest(@RequestBody RequestParameters parameters)
            throws DuplicatedKeyException, EntityNotFoundException {
        int result = 0;
        result = scheduledServiceImp.cancelScheduledRequest(parameters.getPackageId(), parameters.getId());
        if (result == 0)
            return new ResponseEntity<Message>(
                    new Message(MessageType.ERROR, Integer.toString(result),
                            messageSource.getMessage("scheduledRequest.cancel.error", null,
                                    "scheduledRequest.cancel.error", LocaleContextHolder.getLocale())),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, Integer.toString(result),
                messageSource.getMessage("scheduledRequest.cancel.success", null, "scheduledRequest.cancel.success",
                        LocaleContextHolder.getLocale())),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_listonschedule')")
    @RequestMapping(value = "/findallscheduled", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickupRequestInfo>> scheduledRequestFindAll(
            @RequestBody RequestParameters requestparameters) {
        List<PickupRequestInfo> scheduledRequests = null;
        try {
            scheduledRequests = scheduledServiceImp.findAllScheduledRequestsByPage(requestparameters.getPage(),
                    requestparameters.getMaxResult(), requestparameters.getOrderBy(), requestparameters.getId(),
                    requestparameters.getRequesterMobile(), requestparameters.getFromRequestDate(),
                    requestparameters.getToRequestDate(), requestparameters.getFromPickupDate(),
                    requestparameters.getToPickupDate());
            if (scheduledRequests == null)
                return new ResponseEntity<List<PickupRequestInfo>>(scheduledRequests, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<List<PickupRequestInfo>>(scheduledRequests, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<PickupRequestInfo>>(scheduledRequests, HttpStatus.OK);
    }

    @RequestMapping(value = "/countscheduled", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> scheduledRequestsCountAll(@RequestBody RequestParameters requestparameters)
            throws ParseException {
        Integer count = scheduledServiceImp.countAllScheduledRequest(requestparameters.getId(),
                requestparameters.getRequesterMobile(), requestparameters.getFromRequestDate(),
                requestparameters.getToRequestDate(), requestparameters.getFromPickupDate(),
                requestparameters.getToPickupDate());
        return new ResponseEntity<Object>(new Message(MessageType.SUCCESS, (count == null) ? "0" : count.toString(),
                messageSource.getMessage("schedledrequests.countall.success", null, "schedledrequests.countall.success",
                        LocaleContextHolder.getLocale())),
                HttpStatus.OK);
    }
}
