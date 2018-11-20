package com.appzoneltd.lastmile.microservice.freelancedriver.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.FreelanceFilterParameter;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.FreelancerDriverDto;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.FreelancerDriverListInfoDto;
import com.appzoneltd.lastmile.microservice.freelancedriver.service.FreelanceDriverService;
import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FreelancerController {

    @Autowired
    private FreelanceDriverService freelanceDriverService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/findall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FreelancerDriverListInfoDto>> getAllFreelancerDrivers(
            @RequestBody FreelanceFilterParameter endPointParameter) {
        List<FreelancerDriverListInfoDto> freelancersDrivers = null;
        freelancersDrivers = freelanceDriverService.filter(endPointParameter);
        if (freelancersDrivers == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<List<FreelancerDriverListInfoDto>>(freelancersDrivers, HttpStatus.OK);
    }

//    @RequestMapping(value = "/filter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<FreelancerDriverListInfoDto>> filter(
//            @RequestBody FreelanceFilterParameter endPointParameter) {
//        List<FreelancerDriverListInfoDto> freelancersDrivers = null;
//        freelancersDrivers = freelanceDriverService.filter(endPointParameter);
//        if (freelancersDrivers == null)
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return new ResponseEntity<List<FreelancerDriverListInfoDto>>(freelancersDrivers, HttpStatus.OK);
//    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FreelancerDriverDto> getFreelancerDriverById(@RequestBody EndPointParameter endPointParameter)
            throws EntityNotFoundException {
        FreelancerDriverDto freelancersDriver = null;
        freelancersDriver = freelanceDriverService.findFreelancerById(endPointParameter.getId());
        if (freelancersDriver == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<FreelancerDriverDto>(freelancersDriver, HttpStatus.OK);
    }

    @RequestMapping(value = "/countall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> countAllFreelancerDrivers() {

        long count = freelanceDriverService.countAllfreeLancerDriver();

        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, String.valueOf(count), ""), HttpStatus.OK);

    }

    @RequestMapping(value = "/accept", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> acceptFreelancerDrivers(@Validated @RequestBody FreelancerDriverDto freelancerDriverDto)
            throws EntityNotFoundException {

        Object result = freelanceDriverService.acceptFreelancerDriverInfo(freelancerDriverDto);

        if (result instanceof String)
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS,
                    freelancerDriverDto.getUserId().toString(), messageSource.getMessage("driver.create.success",
                    null, "driver.create.success", LocaleContextHolder.getLocale())),
                    HttpStatus.OK);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/reject", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rejectFreelancerDrivers(@RequestBody FreelancerDriverDto freelancerDriverDto)
            throws EntityNotFoundException {

        freelanceDriverService.rejectFreelancerDriverInfo(freelancerDriverDto);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/missingdocuments", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmailWithMissingDocuments(@RequestBody FreelancerDriverDto freelancerDriverDto)
            throws EntityNotFoundException, JsonProcessingException {

        freelanceDriverService.sendEmailWithMissingdoucments(freelancerDriverDto);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/freelancerstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmailWithMissingDocuments() {
        return new ResponseEntity<>(freelanceDriverService.getFreelancerStatus(), HttpStatus.OK);
    }

}
