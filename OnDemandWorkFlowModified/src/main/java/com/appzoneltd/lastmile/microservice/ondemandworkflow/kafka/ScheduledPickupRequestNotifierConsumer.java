package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.service.ScheduledRequestService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ResceduledWorkFlowBase;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.OnDemandWorkflowModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ScheduledPickupRequestNotifierConsumer {

	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private RuntimeService runTimeService;
	
	@Autowired
	private ScheduledRequestService scheduledRequestService;
	
	@Transactional
	@KafkaListener(topics = "SCHEDULED_PICKUP_REQUEST_NOTIFIER")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {

		ResceduledWorkFlowBase resceduledWorkFlowBase = mapper.readValue(payload, ResceduledWorkFlowBase.class);
		MyPrinter.print("SCHEDULED_PICKUP_REQUEST_NOTIFIER Consumer ", resceduledWorkFlowBase.toString());
		if(resceduledWorkFlowBase !=null){
			scheduledRequestService.pushNotificationToDriverOnRequestResceduled(resceduledWorkFlowBase);
		}
	}

}
