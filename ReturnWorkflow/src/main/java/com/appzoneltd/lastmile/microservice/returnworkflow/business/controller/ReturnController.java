package com.appzoneltd.lastmile.microservice.returnworkflow.business.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnParameter;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnRequestModel;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnRescheduledModel;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class ReturnController {

	@Autowired
	private RuntimeService runTimeService;
	

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public void startDelivery(@RequestBody ReturnParameter returnParameter) throws JsonProcessingException {

		// ReturnBase returnBase=new ReturnBase();
		// if(returnParameter.getPackageId()!=null){
		// returnBase.setPackageId(returnParameter.getPackageId());
		// returnW/orkFlowProducer.sendMessage("RETURN_WORKFLOW_START",mapper.writeValueAsString(returnBase));
		
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", returnParameter.getPackageId());
		runTimeService.startProcessInstanceByKey("returnProcess", workflowData);

		// }
	}
	
	@RequestMapping(value = "/scheduleForReturn", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void scheduleForReturn(@Validated @RequestBody ReturnRequestModel returnRequestModel) throws JsonProcessingException {
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", returnRequestModel.getPackageId());
		workflowData.put("returnDate", returnRequestModel.getReturnDate());
		workflowData.put("returnLatitude", returnRequestModel.getLatitude());
		workflowData.put("returnLongitude", returnRequestModel.getLongitude());
		runTimeService.startProcessInstanceByMessage("startScheduledDateMessage", workflowData);
	}
	
	@RequestMapping(value = "/rescheduleForReturn", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void rescheduleForReturn(@Validated @RequestBody ReturnRescheduledModel returnRescheduledModel) throws JsonProcessingException {
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", returnRescheduledModel.getPackageId());
		workflowData.put("returnDate", returnRescheduledModel.getReturnDate());
		runTimeService.startProcessInstanceByMessage("startReScheduledDateMessage", workflowData);
	}
	
}
