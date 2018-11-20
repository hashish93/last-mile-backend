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
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SendDriverAssignedNotificationDelegate implements JavaDelegate {

	@Autowired
	private PickupRequestRepository pickupRequestRepository;
	@Autowired
	private OnDemandNotificationService notificationService;

	@Autowired
	private OnDemandWorkFlowProducer onDemandWorkFlowProducer;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long packageId = (Long) execution.getVariable("packageId");
		Long requestId = (Long) execution.getVariable("requestId");

		Long userId = null;

		MyPrinter.workflowStep("SendDriverAssignedNotificationDelegate step");
		System.out.println("packageId " + packageId);
		System.out.println("requestId " + requestId);

		MyPrinter.print("NOTIFICATION", "WE ARE ON STEP SENDING A NOTIFICATION DRIVER ASSIGNED TO THE USER");
		if (requestId != null) {
			PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
			if (pickupRequestEntity != null) {
				userId = pickupRequestEntity.getRequesterId();
				WorkflowNotificationModel workflowNotificationModel = new WorkflowNotificationModel();
				workflowNotificationModel.setCustomerId(userId);
				workflowNotificationModel.setPackageId(packageId);
				workflowNotificationModel.setRequestId(requestId);

				notificationService.sendDriverAssignedNotification(workflowNotificationModel);

				// STOP Driver Response Timer
				ActionNeededBase actionNeededBase = new ActionNeededBase();
				actionNeededBase.setPackageId(packageId);
				actionNeededBase.setSwitched(false);
				actionNeededBase.setHubId(pickupRequestEntity.getHubId());
				onDemandWorkFlowProducer.sendMessage("AUTOMATIC_ACTION_NEEDED",
						mapper.writeValueAsString(actionNeededBase));

			}
		}
	}

}
