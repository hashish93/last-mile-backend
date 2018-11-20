package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;

@Service
public class SendTrackingNumberNotificationDelegate implements JavaDelegate {

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private OnDemandNotificationService notificationService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" Sending Notification ");
		Long packageId = (Long) execution.getVariable("packageId");
		Long requestId = (Long) execution.getVariable("requestId");
		String trackingNumber = (String) execution.getVariable("trackingNumber");
		System.out.println("SENDING PUSH NOTIFICATION ");

		if (requestId != null) {
			PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
			if (pickupRequestEntity != null) {
				Long requesterId = pickupRequestEntity.getRequesterId();
				WorkflowNotificationModel workflowNotificationModel = new WorkflowNotificationModel();
				workflowNotificationModel.setCustomerId(requesterId);
				workflowNotificationModel.setPackageId(packageId);
				workflowNotificationModel.setRequestId(requestId);
				workflowNotificationModel.setTrackingNumber(trackingNumber);
				MyPrinter.print("NOTIFICATION", "we are about to send notification with tracking number "+workflowNotificationModel);
				notificationService.sendTrackingNumberNotification(workflowNotificationModel);
			}
		}

	}

}