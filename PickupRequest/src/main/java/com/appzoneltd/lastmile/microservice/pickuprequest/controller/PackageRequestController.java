package com.appzoneltd.lastmile.microservice.pickuprequest.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.RequestTypeEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.*;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.PackageRequestInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PackageRequestController {

    private final PackageRequestInfoService packageRequestInfoService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PackageRequestController(PackageRequestInfoService packageRequestInfoService) {
        this.packageRequestInfoService = packageRequestInfoService;
        this.objectMapper = new ObjectMapper();
    }

    @RequestMapping(value = "/listPackage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PackageRequestDto>> listPackage(@RequestBody PagingParameters pagingParameters) {

        List<PackageRequestDto> data = packageRequestInfoService.getPackageRequests(pagingParameters);

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @RequestMapping(value = "/countPackage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> countPackage(@RequestBody PagingParameters pagingParameters) throws IOException {
        MyPrincipal myPrincipal = objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getName(), MyPrincipal.class);
        String data = packageRequestInfoService.getAllPackageRequest(pagingParameters, myPrincipal.getHubs()).size() + "";
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, data, null)
                , HttpStatus.OK);

    }

    @RequestMapping(value = "/listRequestType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object[]> listRequestType() {

        Object[] data = packageRequestInfoService.getRequestTypes();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "/listPackageStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object[]> listPackageStatus() {

        Object[] data = packageRequestInfoService.getPackageStatus();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "/listRequestStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<?>> listRequestStatus() {

        List<RequestTypeEntity> data = packageRequestInfoService.getRequestTypeStatus();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "/packageDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PackageDetailsDto>> pickupHistoryFindAll(
            @RequestBody EndPointParameter requestParameter) {

        List<PackageDetailsDto> data = packageRequestInfoService.getPackageRequestDetails(requestParameter.getId());

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @RequestMapping(value = "/packageTimeLine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PackageTimeLineDto>> packageTimeLineDetails(
            @RequestBody EndPointParameter requestParameter) {
        List<PackageTimeLineDto> data = packageRequestInfoService.getTimeLineForPackage(requestParameter.getId());
        return new ResponseEntity<>(data, HttpStatus.OK);

    }

}
