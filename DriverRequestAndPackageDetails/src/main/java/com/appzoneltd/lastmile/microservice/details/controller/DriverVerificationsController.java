package com.appzoneltd.lastmile.microservice.details.controller;

import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.details.service.PackageVerificationService;
import com.appzoneltd.lastmile.microservice.details.service.component.ConfirmationCodeError;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
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

@RestController
public class DriverVerificationsController {

    private final PackageVerificationService packageVerificationService;
    private final MessageSource messageSource;

    @Autowired
    public DriverVerificationsController(PackageVerificationService packageVerificationService,
                                         MessageSource messageSource) {
        super();
        this.packageVerificationService = packageVerificationService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/verifyPackageDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> verifyPackageDetails(@RequestBody PackageDetails packageDetails)
            throws EntityNotFoundException, JsonProcessingException {
        if (!packageVerificationService.verifyPackageDetails(packageDetails,
                SecurityContextHolder.getContext().getAuthentication()))
            return new ResponseEntity<Message>(new Message(MessageType.ERROR, null)
                    , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> getInvoice(@RequestBody BaseRequestParameter baseRequestParameter)
            throws EntityNotFoundException, JsonProcessingException {
        return new ResponseEntity<Invoice>(packageVerificationService.generateInvoice(baseRequestParameter.getRequestId(), baseRequestParameter.getRequestType(), SecurityContextHolder.getContext().getAuthentication()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmInvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> confirmInvoice(@RequestBody BaseRequestParameter baseRequestParameter)
            throws EntityNotFoundException, JsonProcessingException {
        packageVerificationService.confirmInvoice(SecurityContextHolder.getContext().getAuthentication(),
                baseRequestParameter.getRequestId(), baseRequestParameter.getRequestType());
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmDocuments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> confirmDocuments(@RequestBody Documents documents)
            throws EntityNotFoundException, JsonProcessingException, ConfirmationCodeError {
        if (packageVerificationService.addDocuments(documents, SecurityContextHolder.getContext().getAuthentication()))
            return new ResponseEntity<>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
        return new ResponseEntity<>(new Message(MessageType.ERROR, null), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cancelRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> cancelRequest(@RequestBody CancelRequest cancelRequest)
            throws EntityNotFoundException, JsonProcessingException {
        packageVerificationService.cancelRequest(cancelRequest, SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, null), HttpStatus.OK);
    }
}
