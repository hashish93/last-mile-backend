package com.appzoneltd.lastmile.microservice.pickuprequest.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.MyPickupRequestDTO;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.PickupRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.VerifiedPickupRequestEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest.CancelationRequestDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.RescheduledOndemandDTO;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.UpdateRequestModel;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.PackagePickupProducer;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowCancelRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.CustomerAlternativesDTO;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.PackageHub;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.RequestRating;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.NotUpdatableRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.PickupServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PickupRequestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PickupRequestController.class);

    private final PickupServiceImp pickupServiceImp;
    private final MessageSource messageSource;

    @Autowired
    private PackagePickupProducer packagePickupProducer;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    public PickupRequestController(PickupServiceImp pickupServiceImp, MessageSource messageSource) {
        this.pickupServiceImp = pickupServiceImp;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/createpickuprequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRequest(@RequestBody @Validated PickupRequest pickupRequest)
            throws JsonProcessingException {
        Long requestId = null;
        try {
            requestId = pickupServiceImp.savePickupRequest(pickupRequest);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<Message>(
                    new Message(MessageType.ERROR,
                            null, messageSource.getMessage("pickuprequest.create.error", null,
                            "pickuprequest.create.error", LocaleContextHolder.getLocale())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestId == null) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.ERROR,
                            null, messageSource.getMessage("pickuprequest.create.error", null,
                            "pickuprequest.create.error", LocaleContextHolder.getLocale())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } // end if Condition


        //// Starting WorkFlow to Serve the Pickup Request
        WorkflowBase workflowBase = new WorkflowBase();
        workflowBase.setPackageId(pickupRequest.getPackageId());
        workflowBase.setRequestType(pickupRequest.getRequestType());
        workflowBase.setRequestId(requestId);
        packagePickupProducer.sendMessage("ONDEMAND_WORKFLOW_START",
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));

        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, Long.toString(requestId),
                messageSource.getMessage("pickuprequest.create.success", null, "pickuprequest.create.success",
                        LocaleContextHolder.getLocale())),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/updateRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> updateRequest(@RequestBody UpdateRequestModel updateRequestModel) throws EntityNotFoundException {
        try {
            pickupServiceImp.updateRequest(updateRequestModel);
        } catch (NotUpdatableRequest e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new Message(MessageType.ERROR, messageSource.getMessage(e.getMessage(), null, e.getMessage(), LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(MessageType.SUCCESS, ""), HttpStatus.OK);
    }

    @RequestMapping(value = "/findbyid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickupRequest> findById(@RequestBody EndPointParameter requestparameters) {
        PickupRequest pickupRequest = null;
        try {
            pickupRequest = pickupServiceImp.pickupRequestFindById(requestparameters.getId());
            if (pickupRequest == null)
                return new ResponseEntity<PickupRequest>(pickupRequest, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<PickupRequest>(pickupRequest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<PickupRequest>(pickupRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/findMyRequests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MyPickupRequestDTO>> findRequestByUserId() {
        List<MyPickupRequestDTO> pickupRequests = null;
        try {
            pickupRequests = pickupServiceImp.getRequestForUser(SecurityContextHolder.getContext().getAuthentication());
            if (pickupRequests == null)
                return new ResponseEntity<List<MyPickupRequestDTO>>(pickupRequests, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<List<MyPickupRequestDTO>>(pickupRequests, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<MyPickupRequestDTO>>(pickupRequests, HttpStatus.OK);
    }

    @RequestMapping(value = "/findPackageHub", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findPackageHub(@RequestBody EndPointParameter requestparameters) {

        Long hubId = pickupServiceImp.getPackageHubId(requestparameters.getId());
        if (hubId == null) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.ERROR, null,
                            messageSource.getMessage("pickuprequest.findpackagehub.error", null,
                                    "pickuprequest.findpackagehub.error", LocaleContextHolder.getLocale())),
                    HttpStatus.CONFLICT);
        } else {
            PackageHub packageHub = new PackageHub();
            packageHub.setHubId(hubId);
            return new ResponseEntity<PackageHub>(packageHub, HttpStatus.OK);
        } // end else
    }

    @RequestMapping(value = "/rating", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ratingDriverAndService(@RequestBody RequestRating requestRating) {

        boolean result = pickupServiceImp.ratingDriverAndService(requestRating);
        if (!result) {
            return new ResponseEntity<Message>(
                    new Message(MessageType.ERROR, null,
                            messageSource.getMessage("pickuprequest.ratingservice.error", null,
                                    "pickuprequest.ratingservice.error", LocaleContextHolder.getLocale())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            return new ResponseEntity<Message>(
                    new Message(MessageType.SUCCESS, null, messageSource.getMessage("pickuprequest.rating.success",
                            null, "pickuprequest.rating.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);
        } // end else
    }

    //        @Auditable(action = Action.CANCEL , screenName = Screen.ON_DEMAND)
    @RequestMapping(value = "/adminCancelRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> adminCancelRequest(@RequestBody CancelationRequestDto cancelationRequestDto)
            throws EntityNotFoundException {

//        result = pickupServiceImp.cancelRequestWithReason(cancelationRequestDto.getPackageId(),
//                cancelationRequestDto.getReasonId(), cancelationRequestDto.getDescription());
//        if (result == 0) {
//            return new ResponseEntity<Message>(
//                    new Message(MessageType.ERROR, cancelationRequestDto.getPackageId().toString(),
//                            messageSource.getMessage("Pickuprequest.cancel.request", null,
//                                    "Pickuprequest.cancel.request", LocaleContextHolder.getLocale())),
//                    HttpStatus.CONFLICT);
//        } // end if

        WorkflowCancelRequest workflowCancelRequest = new WorkflowCancelRequest();

        workflowCancelRequest.setPackageId(cancelationRequestDto.getPackageId());
        workflowCancelRequest.setAdmin(true);
        workflowCancelRequest.setRequesterId(cancelationRequestDto.getRequesterId());
        workflowCancelRequest.setRequestId(cancelationRequestDto.getRequestId());
        workflowCancelRequest.setReasonId(cancelationRequestDto.getReasonId());
        workflowCancelRequest.setDescription(cancelationRequestDto.getDescription());
        // Sending Cancel Order to Kafka to trigger workflow
        try {
            packagePickupProducer.sendMessage("CancelPickupRequest",
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowCancelRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } // end try_atch Block

        return new ResponseEntity<Message>(
                new Message(MessageType.SUCCESS, Integer.toString(1), messageSource.getMessage(
                        "request.cancel.success", null, "request.cancel.success", LocaleContextHolder.getLocale())),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/userCancelRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> userCancelRequest(@RequestBody CancelationRequestDto cancelationRequestDto)
            throws EntityNotFoundException {

        System.out.println("DTO OF CANCEL  " + cancelationRequestDto.toString());
        cancelationRequestDto.setReasonId(0L);
        cancelationRequestDto.setDescription("Cancelled By User");

        Long requestIdForPackage = pickupServiceImp.requestIdForPackage(cancelationRequestDto.getPackageId());


        WorkflowCancelRequest workflowCancelRequest = new WorkflowCancelRequest();

        workflowCancelRequest.setPackageId(cancelationRequestDto.getPackageId());
        workflowCancelRequest.setAdmin(false);
        workflowCancelRequest.setRequestId(requestIdForPackage);
        workflowCancelRequest.setReasonId(0L);
        workflowCancelRequest.setDescription(cancelationRequestDto.getDescription());
        // Sending Cancel Order to Kafka to trigger workflow
        System.out.println("cancel request controller " + workflowCancelRequest.toString());
        try {
            packagePickupProducer.sendMessage("CancelPickupRequest",
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowCancelRequest));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // end try_atch Block

        return new ResponseEntity<Message>(
                new Message(MessageType.SUCCESS, Integer.toString(1), messageSource.getMessage(
                        "request.cancel.success", null, "request.cancel.success", LocaleContextHolder.getLocale())),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/isTrackedRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> checkRequestAvailability(@RequestBody EndPointParameter requestparameters) {
        Boolean isTracked = pickupServiceImp.checkTrackedPickupRequest(requestparameters.getId());
        Message message = new Message(MessageType.ERROR, isTracked.toString());
        if (isTracked)
            message.setMessageType(MessageType.SUCCESS);

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/verifiedPickupRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VerifiedPickupRequestEntity> getVerifiedPickupRequest(
            @RequestBody EndPointParameter requestparameters) throws EntityNotFoundException {
        VerifiedPickupRequestEntity pickupRequest = null;
        pickupRequest = pickupServiceImp.getVerifiedPickupRequest(requestparameters.getId());
        return new ResponseEntity<VerifiedPickupRequestEntity>(pickupRequest, HttpStatus.OK);
    }

    //    @Auditable(action = Action.NOTIFY , screenName = Screen.ON_DEMAND)
    @RequestMapping(value = "/sendCustomerAlternatives", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> sendCustomerAlternatives(@RequestBody CustomerAlternativesDTO params) {
        boolean result = true;
        params.setWebUser(true);
//        result = pickupServiceImp.changePickupRequestStatus(params.get("packageId"), OrderStatus.WAITING_FOR_CUSTOMER_ALTERNATIVE.name());
        if (!result) return new ResponseEntity<Message>(
                new Message(MessageType.ERROR, null, null),
                HttpStatus.CONFLICT);
        try {
            packagePickupProducer.sendMessage("SEND_ALTERNATIVES_TO_CUSTOMER",
                    mapper.writeValueAsString(params));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(
                new Message(MessageType.SUCCESS, null),
                HttpStatus.OK);

    }

    //    @Auditable(action = Action.EDIT , screenName = Screen.ON_DEMAND)
    @RequestMapping(value = "/scheduleOnDemandRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> scheduleOnDemandRequest(@RequestBody RescheduledOndemandDTO rescheduledOndemandDTO) {
        boolean result = true;
        rescheduledOndemandDTO.setWebUser(true);
//        result = pickupServiceImp.changePickupRequestStatus(params.get("packageId"), OrderStatus.WAITING_FOR_CUSTOMER_ALTERNATIVE.name());
        if (!result) return new ResponseEntity<Message>(
                new Message(MessageType.ERROR, null, null),
                HttpStatus.CONFLICT);
        try {
            packagePickupProducer.sendMessage("SCHEDULE_ONDEMAND_PICKUP_REQUEST",
                    mapper.writeValueAsString(rescheduledOndemandDTO));
        } catch (JsonProcessingException e) {
            LOGGER.error("ERROR WITH PARSING: {}", e.getMessage());
        }
        return new ResponseEntity<Message>(
                new Message(MessageType.SUCCESS, null),
                HttpStatus.OK);

    }
}
