package com.appzoneltd.lastmile.microservice.details.controller;

import com.appzoneltd.lastmile.microservice.details.dto.JobOrder;
import com.appzoneltd.lastmile.microservice.details.service.GoExtraService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alaa.nabil on 5/18/2017.
 */
@RestController
public class GoExtraController {
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final GoExtraService goExtraService;
    private final MessageSource messageSource;

    @Autowired
    public GoExtraController(KafkaTemplate<Long, String> kafkaTemplate, GoExtraService goExtraService, MessageSource messageSource) {
        this.kafkaTemplate = kafkaTemplate;
        this.goExtraService = goExtraService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/goExtraOrders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> goExtraOrders() {
        List<JobOrder> unPickedGoExtraOrders = null;
        try {
            unPickedGoExtraOrders = goExtraService.getUnPickedGoExtraOrders(SecurityContextHolder.getContext().getAuthentication());
        } catch (Exception e) {
            new ResponseEntity<>(new Message(MessageType.ERROR, messageSource.getMessage(e.getMessage(), null, e.getMessage(), LocaleContextHolder.getLocale()), null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(unPickedGoExtraOrders, HttpStatus.OK);
    }

    @RequestMapping(value = "/pickGoExtraOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> pickGoExtraOrder(@RequestBody Map<String, Long> param)
            throws EntityNotFoundException, JsonProcessingException {
        if (goExtraService.isAcceptedPackage(param.get("packageId")))
            return new ResponseEntity<>(new Message(MessageType.ERROR, "ASSIGNED_REQUEST", messageSource.getMessage("request.assigned", null, "Already Assigned Request", LocaleContextHolder.getLocale())),
                    HttpStatus.CONFLICT);

        Map<String, Object> map = new HashMap<>();
        map.put("packageId", param.get("packageId"));
        map.put("response", true);
        map.put("driverId", goExtraService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName()));
        kafkaTemplate.send("DRIVER_RESPONSE_ONDEMAND_PICKUP_REQUEST", new ObjectMapper().writeValueAsString(map));
        return new ResponseEntity<>(new Message(MessageType.SUCCESS, null, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/acceptOnDemandPickup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> acceptOnDemandPickupRequest(@RequestBody Map<String, Long> param)
            throws EntityNotFoundException, JsonProcessingException {
        if (goExtraService.isActionNeededPackage(param.get("packageId")))
            return new ResponseEntity<>(new Message(MessageType.ERROR, "ACTION_NEEDED_REQUEST", messageSource.getMessage("request.action_needed", null, "Not available to accept or reject", LocaleContextHolder.getLocale())),
                    HttpStatus.CONFLICT);

        if (goExtraService.isAcceptedPackage(param.get("packageId")))
            return new ResponseEntity<>(new Message(MessageType.ERROR, "ASSIGNED_REQUEST", messageSource.getMessage("request.assigned", null, "Already Assigned Request", LocaleContextHolder.getLocale())),
                    HttpStatus.CONFLICT);

        Map<String, Object> map = new HashMap<>();
        map.put("packageId", param.get("packageId"));
        map.put("response", true);
        map.put("driverId", goExtraService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName()));
        kafkaTemplate.send("DRIVER_RESPONSE_ONDEMAND_PICKUP_REQUEST", new ObjectMapper().writeValueAsString(map));
        return new ResponseEntity<>(new Message(MessageType.SUCCESS, null, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/rejectOnDemandPickup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> rejectOnDemandPickupRequest(@RequestBody Map<String, Long> param)
            throws EntityNotFoundException, JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("packageId", param.get("packageId"));
        map.put("response", false);
        map.put("driverId", goExtraService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName()));
        kafkaTemplate.send("DRIVER_RESPONSE_ONDEMAND_PICKUP_REQUEST", new ObjectMapper().writeValueAsString(map));
        return new ResponseEntity<>(new Message(MessageType.SUCCESS, null, null), HttpStatus.OK);
    }
}
