package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate.notification;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
@Component
public class ScheduleOndemandNotificationDelegate implements JavaDelegate {

	@Autowired
	private OnDemandNotificationService notificationService;

	@Autowired
	private RequestHistoryRepository requestHistoryRepository;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long requestId = (Long) execution.getVariable("requestId");
		Long packageId = (Long) execution.getVariable("packageId");
		Long requesterId = (Long) execution.getVariable("requesterId");
		if (requestId != null) {
			packageId = (packageId != null) ? packageId : requestHistoryRepository.getPackageId(requestId);
			requesterId = (requesterId != null) ? requesterId
					: pickupRequestRepository.findOne(requestId).getRequesterId();

			Date pickupDate = (Date) execution.getVariable("pickupDate");
			String timeFrom = (String) execution.getVariable("from");
			String timeTo = (String) execution.getVariable("to");

			WorkflowNotificationModel workflowNotificationModel = new WorkflowNotificationModel();
			workflowNotificationModel.setCustomerId(requesterId);
			workflowNotificationModel.setPackageId(packageId);
			workflowNotificationModel.setRequestId(requestId);

			notificationService.sendScheduleOndemandPickupNotificationToCustomer(workflowNotificationModel, pickupDate,
					timeFrom, timeTo);
		}

	}
}
