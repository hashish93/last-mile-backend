package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.OnDemandWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ActionNeededBase;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.CustomerAlternativesDTO;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.RescheduledOndemandDTO;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowCancelRequest;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowDriverResponse;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.OnDemandWorkflowModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class WorkflowController {

	@Autowired
	private OnDemandWorkFlowProducer onDemandWorkFlowProducer;

	@Autowired
	private ObjectMapper mapper;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/timeout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void timout(@RequestBody ActionNeededBase actionNeededBase) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", actionNeededBase.toString());
		if (actionNeededBase.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("AUTOMATIC_ACTION_NEEDED",
					mapper.writeValueAsString(actionNeededBase));
		}
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/start", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void startPickup(@RequestBody OnDemandWorkflowModel onDemandWorkflowModel) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", onDemandWorkflowModel.toString());
		if (onDemandWorkflowModel.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("ONDEMAND_WORKFLOW_START",
					mapper.writeValueAsString(onDemandWorkflowModel));
		}
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/driverResponse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void driverResponse(@RequestBody WorkflowDriverResponse workflowDriverResponse) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", workflowDriverResponse.toString());
		if (workflowDriverResponse.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("DriverResponseToRequest",
					mapper.writeValueAsString(workflowDriverResponse));
		}
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/driverarrived", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void driverarrived(@RequestBody WorkflowDriverResponse workflowDriverResponse) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", workflowDriverResponse.toString());
		if (workflowDriverResponse.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("DRIVER_ARRIVAL_ACKNOLEDGMENT",
					mapper.writeValueAsString(workflowDriverResponse));
		}
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/requestPickedup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void requestPickedup(@RequestBody WorkflowDriverResponse workflowDriverResponse) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", workflowDriverResponse.toString());
		if (workflowDriverResponse.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("DRIVER_SUBMIT_PICKUP_REQUEST",
					mapper.writeValueAsString(workflowDriverResponse));
		}
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/cancelRequestPickedup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cancelRequestPickedup(@RequestBody WorkflowCancelRequest workflowCancelRequest) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", workflowCancelRequest.toString());
		if (workflowCancelRequest.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("CancelPickupRequest",
					mapper.writeValueAsString(workflowCancelRequest));
		}
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/waitingCustomerAlternatives", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void waitingCustomerAlternatives(@RequestBody CustomerAlternativesDTO customerAlternativesDTO) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", customerAlternativesDTO.toString());
		if (customerAlternativesDTO.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("SEND_ALTERNATIVES_TO_CUSTOMER",
					mapper.writeValueAsString(customerAlternativesDTO));
		}
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/rescheduleOnDemand", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void rescheduleOnDemand(@RequestBody RescheduledOndemandDTO rescheduledOndemandDTO) throws JsonProcessingException {
		MyPrinter.print("CONTROLLER ", rescheduledOndemandDTO.toString());
		if (rescheduledOndemandDTO.getPackageId() != null) {
			onDemandWorkFlowProducer.sendMessage("SCHEDULE_ONDEMAND_PICKUP_REQUEST",
					mapper.writeValueAsString(rescheduledOndemandDTO));
		}
	}

}
