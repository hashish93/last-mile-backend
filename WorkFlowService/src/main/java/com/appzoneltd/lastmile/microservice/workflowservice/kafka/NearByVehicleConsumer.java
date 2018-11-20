package com.appzoneltd.lastmile.microservice.workflowservice.kafka;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflowservice.handler.NearByVehicleHandler;
import com.appzoneltd.lastmile.microservice.workflowservice.handler.NearByVehicleHandlerImpl;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowDispatchMode;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageHub;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageLocation;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageType;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageWeight;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPickupDiameter;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPickupRequestInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class NearByVehicleConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;
	
	@Autowired
	private NearByVehicleHandlerImpl handler;

	/*
	 * Collector Consumer
	 */

	@KafkaListener(topics = "NearByVehicleRequest")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		WorkflowBase workflowBase = mapper.readValue(payload, WorkflowBase.class);
		System.out.println(">>>>>>>> START SEARCHING FOR NEAR BY VEHICLES WITH {} "+workflowBase.toString());
		// Getting related Hub for the Package PickUp Location
		workflowServiceProducer.sendMessage("PackageHubIdRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));

		workflowServiceProducer.sendMessage("PackagePickupLocationRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));

		workflowServiceProducer.sendMessage("PickDiameterConfigRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));

		workflowServiceProducer.sendMessage("PickupDispatchingModeRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));
		
		workflowServiceProducer.sendMessage("PackageWeightRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));
		
		workflowServiceProducer.sendMessage("PackagePickupRequestInfoRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));
		
		workflowServiceProducer.sendMessage("PackageTypeRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowBase));
		
	}

	@KafkaListener(topics = "PackagePickupLocationResponse")
	public void packagePickupLocationResponse(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		WorkflowPackageLocation workflowPackageLocation = mapper.readValue(payload, WorkflowPackageLocation.class);
		System.out.println(">>>>> workflowPackageLocation " + workflowPackageLocation.toString());
		NearByVehicleHandler.getInstance(workflowPackageLocation.getPackageId(),handler).onNext(workflowPackageLocation);
	}

	@KafkaListener(topics = "PackageHubIdResponse")
	public void packageHubIdResponse(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		
		WorkflowPackageHub workflowPackageHub = mapper.readValue(payload, WorkflowPackageHub.class);
		System.out.println(">>> GETTING PICKUP HUB ID FOR PACKAGE WITH {} "+workflowPackageHub.toString());
		System.out.println(">>>>> workflowPackageHub " + workflowPackageHub.toString());
		NearByVehicleHandler.getInstance(workflowPackageHub.getPackageId(),handler).onNext(workflowPackageHub);

	}

	@KafkaListener(topics = "PickDiameterConfigResponse")
	public void pickDiameterConfigResponse(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		WorkflowPickupDiameter workflowPickupDiameter = mapper.readValue(payload, WorkflowPickupDiameter.class);
		System.out.println(">>>>> workflowPickupDiameter " + workflowPickupDiameter.toString());
		NearByVehicleHandler.getInstance(workflowPickupDiameter.getPackageId(),handler).onNext(workflowPickupDiameter);
	}

	@KafkaListener(topics = "PickupDispatchingModeReponse")
	public void pickupDispatchingModeReponse(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		WorkflowDispatchMode workflowDispatchMode = mapper.readValue(payload, WorkflowDispatchMode.class);
		System.out.println(">>>>> workflowDispatchMode " + workflowDispatchMode.toString());
		NearByVehicleHandler.getInstance(workflowDispatchMode.getPackageId(),handler).onNext(workflowDispatchMode);
	}
	
	@KafkaListener(topics = "PackageWeightResponse")
	public void packageWeightResponse(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		WorkflowPackageWeight workflowPackageWeight = mapper.readValue(payload, WorkflowPackageWeight.class);
		System.out.println(">>>>> workflowPackageWeight " + workflowPackageWeight.toString());
		NearByVehicleHandler.getInstance(workflowPackageWeight.getPackageId(),handler).onNext(workflowPackageWeight);
	}
	
	@KafkaListener(topics = "PackagePickupRequestInfoResponse")
	public void packagePickupRequestInfoResponse(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		WorkflowPickupRequestInfo workflowPickupRequestInfo = mapper.readValue(payload, WorkflowPickupRequestInfo.class);
		System.out.println(">>>>> WorkflowPickupRequestInfo " + workflowPickupRequestInfo.toString());
		NearByVehicleHandler.getInstance(workflowPickupRequestInfo.getPackageId(),handler).onNext(workflowPickupRequestInfo);
	}
	
	@KafkaListener(topics = "PackageTypeResponse")
	public void packageTypeResponse(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		/// Receiving Payload from Kafka
		WorkflowPackageType workflowPackageType= mapper.readValue(payload, WorkflowPackageType.class);
		System.out.println(">>>>> WorkflowPackageType " + workflowPackageType.toString());
		NearByVehicleHandler.getInstance(workflowPackageType.getPackageId(),handler).onNext(workflowPackageType);
	}

}
