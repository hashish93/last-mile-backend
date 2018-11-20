package com.appzoneltd.lastmile.microservice.workflow.kafka;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.RescheduledOndemandDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
@Component
public class ScheduleOndemandRequestConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerAlternativesConsumer.class);
    private final RuntimeService runtimeService;
    private final ObjectMapper mapper;

    @Autowired
    public ScheduleOndemandRequestConsumer(RuntimeService runtimeService, ObjectMapper mapper) {
        this.runtimeService = runtimeService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "SCHEDULE_ONDEMAND_PICKUP_REQUEST")
    public void schedulePickupRequestListener(@Payload String payload) {
        LOGGER.debug("CONSUMED PAYLOAD: {}", payload);
        RescheduledOndemandDTO rescheduledOndemandDTO = null;
        try {
            rescheduledOndemandDTO = mapper.readValue(payload, RescheduledOndemandDTO.class);
        } catch (IOException e) {
            LOGGER.error("ERROR WITH PARSING RESCHEDULE ONDEMAND PICKUP {}" + payload);
        }
        final Map<String, Object> workFlowVariables = new HashMap<>();
        workFlowVariables.put("packageId", rescheduledOndemandDTO.getPackageId());
        workFlowVariables.put("requestId", rescheduledOndemandDTO.getRequestId());
        workFlowVariables.put("requesterId", rescheduledOndemandDTO.getRequesterId());
        workFlowVariables.put("pickupDate", rescheduledOndemandDTO.getPickupDate());
        workFlowVariables.put("from", rescheduledOndemandDTO.getTimeFrom());
        workFlowVariables.put("to", rescheduledOndemandDTO.getTimeTo());
        workFlowVariables.put("isWebUser", rescheduledOndemandDTO.getWebUser());
        runtimeService.startProcessInstanceByMessage("startScheduleOndemandPickup", workFlowVariables);
    }
}
