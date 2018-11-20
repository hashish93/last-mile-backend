package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.OnDemandWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ActionNeededBase;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SendOutOfRangeNotificationDelegate implements JavaDelegate{

	@Autowired
	private OnDemandNotificationService notificationService;
	
	@Autowired
	private PickupRequestRepository pickupRequestRepository;
	
	@Autowired
	private OnDemandWorkFlowProducer onDemandWorkFlowProducer;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" Sending Notification Out of Range ");
		Long packageId=(Long) execution.getVariable("packageId");
		Long requestId=(Long) execution.getVariable("requestId");
		
		PickupRequestEntity pickupRequestEntity=pickupRequestRepository.findOne(requestId);
		if(pickupRequestEntity!=null){
			Long requesterId = pickupRequestEntity.getRequesterId();			
			WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
			workflowNotificationModel.setCustomerId(requesterId);
			workflowNotificationModel.setPackageId(packageId);
			workflowNotificationModel.setRequestId(requestId);
			// Sending Notification
			notificationService.sendOutOfRangeNotification(workflowNotificationModel);
			
			// STOP Driver Response Timer
			ActionNeededBase actionNeededBase = new ActionNeededBase();
			actionNeededBase.setPackageId(packageId);
			actionNeededBase.setSwitched(false);
			onDemandWorkFlowProducer.sendMessage("AUTOMATIC_ACTION_NEEDED",
					mapper.writeValueAsString(actionNeededBase));
			
		}
		
	}

	
}
