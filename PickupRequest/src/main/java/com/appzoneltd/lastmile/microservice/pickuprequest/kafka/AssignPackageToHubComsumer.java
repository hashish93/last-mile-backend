package com.appzoneltd.lastmile.microservice.pickuprequest.kafka;

import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPackageHub;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.PickupServiceImp;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AssignPackageToHubComsumer {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PickupServiceImp pickupService;

    @Autowired
    private PackagePickupProducer packagePickupProducer;


    @KafkaListener(topics = "AssignPackageToHubRequest")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
        //// Receiving the PackageStatus from Kafka
        WorkflowPackageHub workflowPackageHub = mapper.readValue(payload, WorkflowPackageHub.class);
        // Getting PackageId
        if (workflowPackageHub.getHubId() != null) {
            //Changing Status of the Package
            boolean updated = pickupService.assignPackageToHub(workflowPackageHub.getPackageId(), workflowPackageHub.getHubId());
            System.out.println("Kafka Consumer (AssignPackageToHubComsumer) : PACKAGE ASSIGNED TO HUB  " + updated);
        }//end if condition
        Long customerId = pickupService.getPackageCustomerId(workflowPackageHub.getPackageId());
        workflowPackageHub.setRequesterId(customerId);
        /// Sending that Hub
        packagePickupProducer.sendMessage("PickupHubResponse", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageHub));
    }

}
