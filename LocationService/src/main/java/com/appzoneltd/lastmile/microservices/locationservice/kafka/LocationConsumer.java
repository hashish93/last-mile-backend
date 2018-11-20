package com.appzoneltd.lastmile.microservices.locationservice.kafka;

import com.appzoneltd.lastmile.microservices.locationservice.kafka.model.AddressKafkaModel;
import com.appzoneltd.lastmile.microservices.locationservice.kafka.model.LocationKafkaModel;
import com.appzoneltd.lastmile.microservices.locationservice.model.LocationModel;
import com.appzoneltd.lastmile.microservices.locationservice.service.LocationService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hashish on 5/9/2017.
 */
@Component
public class LocationConsumer {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LocationService locationService;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "LOCATION_TOPIC")
    public void receiveMessage(@Payload String payload) throws Exception {

        LocationKafkaModel locationKafkaModel = mapper.readValue(payload , LocationKafkaModel.class);
        LocationModel location = new LocationModel();
        location.setLatitude(locationKafkaModel.getLatitude());
        location.setLongitude(locationKafkaModel.getLongitude());
        String Address = locationService.getAddressFromLocation(location);
        if(Address != null){
            AddressKafkaModel addressModel = new AddressKafkaModel();
            addressModel.setId(locationKafkaModel.getId());
            addressModel.setAddress(Address);
            kafkaTemplate.send("ADDRESS_TOPIC", objectMapper.writeValueAsString(addressModel));
        }

        System.out.println(Address);
    }

}
