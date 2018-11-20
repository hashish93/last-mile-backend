package com.appzoneltd.lastmile.microservices.locationservice.controller;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservices.locationservice.model.AddressModel;
import com.appzoneltd.lastmile.microservices.locationservice.model.LocationModel;
import com.appzoneltd.lastmile.microservices.locationservice.service.LocationService;
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


/**
 * Created by hashish on 5/9/2017.
 */
@RestController
public class LocationController {

    @Autowired
    MessageSource messageSource;
    @Autowired
    LocationService locationService;
    @RequestMapping(value = "/convertLatLongToLocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> convertLatLongToLocation(@Validated @RequestBody LocationModel locationModel) throws Exception {
        String Address = locationService.getAddressFromLocation(locationModel);
        if (Address != null){
            AddressModel addressModel = new AddressModel();
            addressModel.setAddress(Address);
            return new ResponseEntity<>(addressModel , HttpStatus.OK);
        }

        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
