package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.controller;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryParameter;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.ReschedulingDeliveryDateService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.DeliveryWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.models.DeliveryBase;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DeliveryController {

	@Autowired
	private RuntimeService runTimeService;

	@Autowired
	private DeliveryWorkFlowProducer workFlowProducer;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ReschedulingDeliveryDateService reschedulingDeliveryDateService;

	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public void startDelivery(@RequestBody DeliveryParameter deliveryParameter) throws JsonProcessingException {

		DeliveryBase deliveryBase = new DeliveryBase();
		if (deliveryParameter.getPackageId() != null) {
			deliveryBase.setPackageId(deliveryParameter.getPackageId());
			workFlowProducer.sendMessage("DELIVERY_WORKFLOW_START", mapper.writeValueAsString(deliveryBase));
		}
	}

	@RequestMapping(value = "/rescheduleDeliveryRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rescheduleDeliveryDate(@RequestBody DeliveryParameter deliveryParameter)
			throws JsonProcessingException {
		Long packageId = requestHistoryJpaRepository.getDeliveryPackageId(deliveryParameter.getRequestId());
		List<Message> result = reschedulingDeliveryDateService.rescheduleDelivery(packageId,
				deliveryParameter.getDeliveryDate(), deliveryParameter.getDeliveryTimeFrom(),
				deliveryParameter.getDeliveryTimeTo());

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Message>>(result, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new Message(MessageType.SUCCESS, ""),HttpStatus.OK);
		}

	}

	// @ResponseStatus(value = HttpStatus.OK)
	// @RequestMapping(value = "/driverResponse", method = RequestMethod.POST)
	// public void driverResponseOnPackage(@RequestBody DeliveryParameter
	// deliveryParameter)
	// throws JsonProcessingException {
	// Map<String, Object> workflowData = new HashMap<String, Object>();
	// workflowData.put("packageId", deliveryParameter.getPackageId());
	// workflowData.put("driverResponse",
	// deliveryParameter.isDriverAcceptance());
	// runTimeService.startProcessInstanceByMessage("startDriverPackageConfirmation",
	// workflowData);
	// }
	//
	// @ResponseStatus(value = HttpStatus.OK)
	// @RequestMapping(value = "/driverNavigate", method = RequestMethod.POST)
	// public void driverNavigateToCustomer(@RequestBody DeliveryParameter
	// deliveryParameter)
	// throws JsonProcessingException {
	// Map<String, Object> workflowData = new HashMap<String, Object>();
	// workflowData.put("packageId", deliveryParameter.getPackageId());
	// workflowData.put("driverId", deliveryParameter.getDriverId());
	// runTimeService.startProcessInstanceByMessage("startDriverNavigate",
	// workflowData);
	// }

	// @ResponseStatus(value = HttpStatus.OK)
	// @RequestMapping(value = "/driverArrived", method = RequestMethod.POST)
	// public void driverArrivedToCustomer(@RequestBody DeliveryParameter
	// deliveryParameter)
	// throws JsonProcessingException {
	// Map<String, Object> workflowData = new HashMap<String, Object>();
	// workflowData.put("packageId", deliveryParameter.getPackageId());
	// runTimeService.startProcessInstanceByMessage("startDriverArrived",
	// workflowData);
	// }
	//
	// @ResponseStatus(value = HttpStatus.OK)
	// @RequestMapping(value = "/customerFound", method = RequestMethod.POST)
	// public void checkCustomerFound(@RequestBody DeliveryParameter
	// deliveryParameter) throws JsonProcessingException {
	// Map<String, Object> workflowData = new HashMap<String, Object>();
	// workflowData.put("packageId", deliveryParameter.getPackageId());
	// workflowData.put("customerFound", deliveryParameter.isCustomerFound());
	//
	// runTimeService.startProcessInstanceByMessage("startCustomerFound",
	// workflowData);
	// }

	// @ResponseStatus(value = HttpStatus.OK)
	// @RequestMapping(value = "/customerApproveInvoice", method =
	// RequestMethod.POST)
	// public void customerApproveInvoice(@RequestBody DeliveryParameter
	// deliveryParameter)
	// throws JsonProcessingException {
	// Map<String, Object> workflowData = new HashMap<String, Object>();
	// workflowData.put("packageId", deliveryParameter.getPackageId());
	// workflowData.put("customerApproveInvoice",
	// deliveryParameter.isCustomerApproveInvoice());
	//
	// runTimeService.startProcessInstanceByMessage("startCustomerApproveInvoice",
	// workflowData);
	// }

	// @ResponseStatus(value = HttpStatus.OK)
	// @RequestMapping(value = "/reportEmergency", method = RequestMethod.POST)
	// public void driverReportEmergency(@RequestBody DeliveryParameter
	// deliveryParameter) throws JsonProcessingException {
	// Map<String, Object> workflowData = new HashMap<String, Object>();
	// workflowData.put("driverId", deliveryParameter.getDriverId());
	// runTimeService.startProcessInstanceByMessage("startDriverEmergencyReport",
	// workflowData);
	// }

}
