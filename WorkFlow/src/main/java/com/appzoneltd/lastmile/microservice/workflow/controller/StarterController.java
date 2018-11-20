package com.appzoneltd.lastmile.microservice.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class StarterController {
	
	@Autowired
	private WorkFlowProducer sender;
	
	@Autowired
	private ObjectMapper mapper ;
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/workflow/start" ,method=RequestMethod.POST)
	public void startWorkflowProcesses(@RequestBody WorkflowBase workflowRequest) throws JsonProcessingException{
		sender.sendMessage("LastMileRequest", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowRequest));
	}

}
