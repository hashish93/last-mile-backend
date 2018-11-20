package com.appzoneltd.lastmile.microservice.details.controller;

import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.details.service.CancellationReasonService;
import com.appzoneltd.lastmile.microservice.details.service.PackageLabelService;
import com.appzoneltd.lastmile.microservice.details.service.PackageService;
import com.appzoneltd.lastmile.microservice.details.service.PackageTypeService;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DriverNavigateAndDetailsController {

    private final PackageService packageService;
    private final PackageLabelService labelService;
    private final PackageTypeService typeService;
    private final CancellationReasonService cancellationReasonService;

    @Autowired
    public DriverNavigateAndDetailsController(PackageService packageService, PackageLabelService labelService,
                                              PackageTypeService typeService, CancellationReasonService cancellationReasonService) {
        this.packageService = packageService;
        this.labelService = labelService;
        this.typeService = typeService;
        this.cancellationReasonService = cancellationReasonService;
    }

    @RequestMapping(value = "/startNavigation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> startNavigation(@RequestBody BaseRequestParameter startNavigation) throws EntityNotFoundException, JsonProcessingException {
        return new ResponseEntity<>(
                packageService.startNavigation(SecurityContextHolder.getContext().getAuthentication(), startNavigation.getRequestId(), startNavigation.getRequestType()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPackageDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PackageDetails> findById(@RequestBody PackageDetailsRequest packageDetailsRequest)
            throws EntityNotFoundException, JsonProcessingException {
        PackageDetails packge = packageService.findRequestAndPackageDetailsByPackageId(
                SecurityContextHolder.getContext().getAuthentication(), packageDetailsRequest.getPackageId(),
                packageDetailsRequest.getRequestId(), packageDetailsRequest.getRequestType());
        return new ResponseEntity<>(packge, HttpStatus.OK);
    }

    @RequestMapping(value = "/packagelabel/findAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PackageLabel>> packageLabelFindAllAPI() {
        List<PackageLabel> packageLabels = labelService.packageLabelFindAll();
        return packageLabels == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(packageLabels, HttpStatus.OK);
    }

    @RequestMapping(value = "/packagetype/findAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PackageType>> packageTypeFindAll() {
        List<PackageType> packageTypes = typeService.findAllPackageTypes();
        return packageTypes == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(packageTypes, HttpStatus.OK);
    }

    @RequestMapping(value = "/pickuprequest/findAllReasons", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickupCancellationReason>> cancellationReasons() {
        List<PickupCancellationReason> cancellationReasons = cancellationReasonService.getCancellationReasons();
        return cancellationReasons == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(cancellationReasons, HttpStatus.OK);
    }

}
