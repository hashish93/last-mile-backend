package com.appzoneltd.lastmile.microservice.vehicleregistration.controller;

import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.RegistrationModel;
import com.appzoneltd.lastmile.microservice.vehicleregistration.dto.TrackingDetails;
import com.appzoneltd.lastmile.microservice.vehicleregistration.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

public class UpdateLocationWebSocketController extends TextWebSocketHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateLocationWebSocketController.class);
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RegistrationService registrationService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        session.sendMessage(new TextMessage("SUCCESSFULLY OPENED WITH ID: " + session.getId(), true));
        LOGGER.info("NEW SESSION : " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	System.out.println("inside :::: handleTextMessage >>>> :::"+message.getPayload());
        TrackingDetails trackingDetails = objectMapper.readValue(message.getPayload(), TrackingDetails.class);
        RegistrationModel model = registrationService.updateLocationAndFirebase(trackingDetails.getLocation().getLongitude().toString(),
                trackingDetails.getLocation().getLatitude().toString(),
                trackingDetails.getPushNotificationTokens().getFirebaseToken(), session.getPrincipal());

        if (model != null) {
            session.sendMessage(new TextMessage("SUCCESS", true));
        } else {        	
            session.sendMessage(new TextMessage("ERROR: NO ACTIVE VEHICLE", true));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.error("ERROR::", exception);
        session.sendMessage(new TextMessage(exception.getMessage()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        LOGGER.info("CLOSE SESSION : " + session.getId());
    }

}
