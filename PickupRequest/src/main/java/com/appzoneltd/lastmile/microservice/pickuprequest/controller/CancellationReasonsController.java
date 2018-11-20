package com.appzoneltd.lastmile.microservice.pickuprequest.controller;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest.CancelationRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.CancelationRequestReasonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CancellationReasonsController {

    private final CancelationRequestReasonsService cancellationRequestReasonsService;

    @Autowired
    public CancellationReasonsController(CancelationRequestReasonsService cancellationRequestReasonsService) {
        this.cancellationRequestReasonsService = cancellationRequestReasonsService;

    }

    @RequestMapping(value = "/findallreasons", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CancelationRequest>> findAllCanellationReason() {
        List<CancelationRequest> allReasons = null;
        allReasons = cancellationRequestReasonsService.finadAllReasons();
        return new ResponseEntity<List<CancelationRequest>>(allReasons, HttpStatus.OK);
    }

}
