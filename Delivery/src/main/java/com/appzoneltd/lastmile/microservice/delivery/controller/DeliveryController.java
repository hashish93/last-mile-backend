package com.appzoneltd.lastmile.microservice.delivery.controller;

import com.appzoneltd.lastmile.microservice.delivery.dto.DeliveryRequest;
import com.appzoneltd.lastmile.microservice.delivery.dto.DeliveryRequestDetails;
import com.appzoneltd.lastmile.microservice.delivery.dto.PagingParameters;
import com.appzoneltd.lastmile.microservice.delivery.dto.RequestHistoryTimeLine;
import com.appzoneltd.lastmile.microservice.delivery.service.DeliveryService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
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

import java.security.Principal;
import java.util.List;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
@RestController
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/viewDeliveries", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeliveryRequest>> getAllDeliveries(@RequestBody PagingParameters pagingParameters) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (pagingParameters.getPage() <= 0)
            return new ResponseEntity<List<DeliveryRequest>>(deliveryService.getDeliveryRequests(pagingParameters, principal), HttpStatus.OK);
        else
            return new ResponseEntity<List<DeliveryRequest>>(deliveryService.getDeliveryRequestsByPageAndOffset(pagingParameters, principal), HttpStatus.OK);
    }

    @RequestMapping(value = "/getDeliveryStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDeliveryStatus() {
        return new ResponseEntity<>(deliveryService.getDeliveryStatus(), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/getDeliveryStatusList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDeliveryStatusList() {
        return new ResponseEntity<>(deliveryService.getDeliveryStatusOnly(), HttpStatus.OK);
    }

    
    @RequestMapping(value = "/archivedDeliveries", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> archivedDeliveries(@RequestBody EndPointParameter requestParameter) {
        return new ResponseEntity<>(deliveryService.fetchArchivedDeliveries(requestParameter, SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
    }

    @RequestMapping(value = "/countArchivedDeliveries", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> countArchivedDeliveries() {
        return new ResponseEntity<>(deliveryService.countArchivedDeliveries(SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
    }

    @RequestMapping(value = "/countDeliveries", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> countDeliveries(@RequestBody PagingParameters pagingParameters) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, deliveryService.getDeliveryRequestsCount(pagingParameters, principal).toString(), null)
                , HttpStatus.OK);
    }

    @RequestMapping(value = "/getDeliveryDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeliveryRequestDetails> getDeliveryDetails(@RequestBody PagingParameters pagingParameters) {
        DeliveryRequestDetails deliveryRequestDetails = deliveryService.getDeliveryRequestDetailsById(pagingParameters.getId());
        if (deliveryRequestDetails == null)
            return new ResponseEntity<DeliveryRequestDetails>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<DeliveryRequestDetails>(deliveryRequestDetails, HttpStatus.OK);
    }

//    @RequestMapping(value = "/rescheduleDeliveryRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Message> rescheduleDeliveryRequest(@RequestBody DeliveryRequest deliveryRequest) {
//        DeliveryRequestEntity deliveryRequestEntity = null;
//        try {
//            deliveryRequestEntity = deliveryService.rescheduleDelivery(deliveryRequest.getRequestId(), deliveryRequest.getDeliveryDate(), deliveryRequest.getDeliveryTimeFrom(), deliveryRequest.getDeliveryTimeTo());
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(new Message(MessageType.ERROR, messageSource.getMessage("request.notfound", null, LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
//        } catch (JsonProcessingException e) {
//            return new ResponseEntity<>(new Message(MessageType.ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//        if (deliveryRequestEntity == null)
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(new Message(MessageType.SUCCESS, messageSource.getMessage("delivery.rescheduled.successfully", null, LocaleContextHolder.getLocale())), HttpStatus.OK);
//    }

    //    @PreAuthorize("hasRole('ROLE_historyrequest')")
    @RequestMapping(value = "/timeline", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestHistoryTimeLine>> timeLineFindAll(
            @RequestBody EndPointParameter requestParameter) {
        List<RequestHistoryTimeLine> timeLineData = deliveryService.deliveryRequestTimeLine(requestParameter.getId());
        return new ResponseEntity<>(timeLineData, HttpStatus.OK);

    }

}
