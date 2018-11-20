package com.appzoneltd.lastmile.microservice.building.kafka;

import com.appzoneltd.lastmile.microservice.building.ServingArea.service.BuildingServingAreaService;
import com.appzoneltd.lastmile.microservice.building.kafka.models.WorkflowPackageHub;
import com.appzoneltd.lastmile.microservice.building.kafka.models.WorkflowPackageLocation;
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
public class PickupHubConsumer {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(PickupHubConsumer.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BuildingServingAreaService buildingServingAreaService;
    @Autowired
    private PickupHubProducer pickupHubProducer;

	@KafkaListener(topics = "CheckPackagePickupHubRequest")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
        /// Receiving Payload from Kafka
        WorkflowPackageLocation workflowPackageLocation = mapper.readValue(payload, WorkflowPackageLocation.class);
        // Log Payload
        LOGGER.info(">> Kafka Consumer (workflowPackageLocation) : " + workflowPackageLocation.toString());
        // Getting related Hub for the Package PickUp Location
        Long hubId = buildingServingAreaService.gettingPickRequestServingAreaHubBuilding(workflowPackageLocation.getLatitude(),
                workflowPackageLocation.getLongitude());
        /// Preparing to Response to WorkFlow
        WorkflowPackageHub workflowPackageHub = new WorkflowPackageHub();
        workflowPackageHub.setPackageId(workflowPackageLocation.getPackageId());
        workflowPackageHub.setHubId(hubId);
        workflowPackageHub.setRequestType(workflowPackageLocation.getRequestType());
        /// AssignPackageToHub Request if Found
        pickupHubProducer.sendMessage("AssignPackageToHubRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageHub));
    }

}
