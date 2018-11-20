package com.appzoneltd.lastmile.microservice.pickuprequest.kafka;

import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPackageLocation;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.PackagePickupLocation;
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
public class PackagePickupLocationConsumer {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(PackagePickupLocationConsumer.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PackagePickupProducer changePackageStatusProducer;
    @Autowired
    private PickupServiceImp pickupServiceImp;

    @KafkaListener(topics = "PackagePickupLocationRequest")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
        // Getting Payload From Kafka
        WorkflowBase workflowBase = mapper.readValue(payload, WorkflowBase.class);
        // View Data
        LOGGER.info("Kafka Consumer (PackagePickupLocationConsumer) : " + workflowBase.toString());

        PackagePickupLocation location = pickupServiceImp.getPackageLocation(workflowBase.getPackageId());

        // Prepare the Package Location Object
        WorkflowPackageLocation workflowPackageLocation = new WorkflowPackageLocation();
        workflowPackageLocation.setPackageId(workflowBase.getPackageId());
        workflowPackageLocation.setLatitude(location.getLatitude());
        workflowPackageLocation.setLongitude(location.getLongitude());

        // Send to Kafka
        changePackageStatusProducer.sendMessage("PackagePickupLocationResponse", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageLocation));
    }

    @KafkaListener(topics = "CheckPackagePickupLocationRequest")
    public void assignPackagePickupHubRequest(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
        // Getting Payload From Kafka
        WorkflowBase workflowBase = mapper.readValue(payload, WorkflowBase.class);
        // View Data
        LOGGER.info("Kafka Consumer (PackagePickupLocationConsumer) : " + workflowBase.toString());

        PackagePickupLocation location = pickupServiceImp.getPackageLocation(workflowBase.getPackageId());

        // Prepare the Package Location Object
        WorkflowPackageLocation workflowPackageLocation = new WorkflowPackageLocation();
        workflowPackageLocation.setPackageId(workflowBase.getPackageId());
        workflowPackageLocation.setLatitude(location.getLatitude());
        workflowPackageLocation.setLongitude(location.getLongitude());
        workflowPackageLocation.setRequestType(workflowBase.getRequestType());

        // Send to Kafka
        changePackageStatusProducer.sendMessage("CheckPackagePickupHubRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageLocation));
    }

}
