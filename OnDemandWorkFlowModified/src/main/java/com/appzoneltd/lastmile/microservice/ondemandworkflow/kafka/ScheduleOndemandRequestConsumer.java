package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.RescheduledOndemandDTO;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
@Component
public class ScheduleOndemandRequestConsumer {
    
	@Autowired
    private RuntimeService runtimeService;
	@Autowired
    private ObjectMapper mapper;

   

    @KafkaListener(topics = "SCHEDULE_ONDEMAND_PICKUP_REQUEST")
    public void schedulePickupRequestListener(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
    	RescheduledOndemandDTO rescheduledOndemandDTO = mapper.readValue(payload, RescheduledOndemandDTO.class);
    	MyPrinter.print("ScheduleOndemandRequestConsumer","rescheduledOndemandDTO "+rescheduledOndemandDTO.toString());
        Map<String, Object> workFlowVariables = new HashMap<>();
        workFlowVariables.put("packageId", rescheduledOndemandDTO.getPackageId());
        workFlowVariables.put("requestId", rescheduledOndemandDTO.getRequestId());
        workFlowVariables.put("requesterId", rescheduledOndemandDTO.getRequesterId());
        workFlowVariables.put("pickupDate", rescheduledOndemandDTO.getPickupDate());
        workFlowVariables.put("from", rescheduledOndemandDTO.getTimeFrom());
        workFlowVariables.put("to", rescheduledOndemandDTO.getTimeTo());
        workFlowVariables.put("admin", rescheduledOndemandDTO.isAdmin());
    	MyPrinter.print("CONSUMER", "payload "+workFlowVariables);
        runtimeService.startProcessInstanceByMessage("startScheduleOndemandPickup", workFlowVariables);
    }
}
