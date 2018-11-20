package com.appzoneltd.lastmile.microservice.pickuprequest.kafka;

import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPackageStatus;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.PickupServiceImp;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ChangePickupRequestStatusConsumer {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ChangePickupRequestStatusConsumer.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PickupServiceImp pickupService;

    @KafkaListener(topics = "ChangePickupRequestStatusRequest")
    public void changePickupRequestStatusRequest(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
        boolean updated = false;
        //// Getting Data from Kafka
        WorkflowPackageStatus workflowPackageStatus = mapper.readValue(payload, WorkflowPackageStatus.class);
        // Viewing Log
        LOGGER.info("Kafka Consumer (ChangePickupRequestStatusConsumer) : " + workflowPackageStatus.toString());
        // Getting PackageId
        Long packageId = workflowPackageStatus.getPackageId();
        if (ChangePackageStatusEnum.SCHEDULED_FOR_PICKUP.equals(workflowPackageStatus.getStatus()) && workflowPackageStatus.getIsWebUser())
            pickupService.scheduleOndemandPickupRequest(workflowPackageStatus.getPackageId(),
                    workflowPackageStatus.getRequestId(), workflowPackageStatus.getPickupDate(),
                    workflowPackageStatus.getTimeFrom(), workflowPackageStatus.getTimeTo());
        else {
            //Changing Status of the Request
            updated = pickupService.changePickupRequestStatus(packageId, workflowPackageStatus.getStatus().getPackageStatus(),workflowPackageStatus.getCancelReason());
        }
        // View Result Log
        LOGGER.info("Kafka Consumer (ChangePickupRequestStatusConsumer) : updated  " + updated);
    }//end changePickupRequestStatusRequest
}
