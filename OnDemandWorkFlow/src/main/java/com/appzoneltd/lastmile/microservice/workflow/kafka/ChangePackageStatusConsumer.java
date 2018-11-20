package com.appzoneltd.lastmile.microservice.workflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.appzoneltd.lastmile.microservice.workflow.handler.ProcessServicesHandler;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageStatus;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.functions.Action;

@Component

public class ChangePackageStatusConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "ChangePackageStatusResponse", group = "ChangePackageStatusResponse")
	public void receiveMessage(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {

		WorkflowPackageStatus workflowPackageStatus = mapper.readValue(payload, WorkflowPackageStatus.class);
		try {
			doReceiveMessage(workflowPackageStatus);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}

	}

	private void doReceiveMessage(WorkflowPackageStatus workflowPackageStatus) {
		String waitState = workflowPackageStatus.getStatus().getWaitStateName(workflowPackageStatus.getIsWebUser());
		ProcessServicesHandler.get(workflowPackageStatus.getPackageId(), waitState)
				.serviceConsumed(changePackageStatus(workflowPackageStatus));
	}

	private Action changePackageStatus(final WorkflowPackageStatus workflowPackageStatus) {

		return new Action() {

			@Override
			public void run() throws Exception {
				/// generate Map to Complete Flow
				Map<String, Object> workflowData = new HashMap<String, Object>();
				workflowData.put("packageId", workflowPackageStatus.getPackageId());

				ProcessInstance processInstance = null;
				List<ProcessInstance> processInstances = runTimeService.createProcessInstanceQuery()
						.variableValueEquals("packageId", workflowPackageStatus.getPackageId()).list();

				if (processInstances != null && !processInstances.isEmpty()) {
					processInstance = processInstances.get(0);
				}

				Execution execution = null;
				// Response With Status
				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.NEW)) {
					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("waitAssingPackageAsNew").singleResult();
				}

				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE)
						&& !workflowPackageStatus.getIsWebUser()) {
					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("waitAssingPackageAsWaitingForCustomer").singleResult();
				} // end if Condition

				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.WAITING_FOR_CUSTOMER_ALTERNATIVE)
						&& workflowPackageStatus.getIsWebUser()) {
					workflowData.put("requestId", workflowPackageStatus.getRequestId());
					workflowData.put("requesterId", workflowPackageStatus.getRequesterId());
					workflowData.put("isWebUser", workflowPackageStatus.getIsWebUser());
					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("WaitCustomerAlternative").singleResult();
				}

				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.AWAITING_PICKUP)) {
					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("WaitForAssingPackageAsAWaitingPickup").singleResult();
				}

				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.CANCELED)) {
					workflowData.put("admin", workflowPackageStatus.isAdmin());
					workflowData.put("userId", workflowPackageStatus.getUserId());
					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("WaitUntilPackageStatusChangedToCancelled").singleResult();
				}
				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.PICKEDUP)) {

					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("WaitUntilPackageStatusChangedToPickedUp").singleResult();
				}

				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.SCHEDULED_FOR_PICKUP)) {
					workflowData.put("requestId", workflowPackageStatus.getRequestId());
					workflowData.put("requesterId", workflowPackageStatus.getRequesterId());
					workflowData.put("pickupDate", workflowPackageStatus.getPickupDate());
					workflowData.put("from", workflowPackageStatus.getTimeFrom());
					workflowData.put("to", workflowPackageStatus.getTimeTo());
					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("waitChangingPackageToScheduledForPickup").singleResult();
				} // end if Condition

				if (workflowPackageStatus.getStatus().equals(ChangePackageStatusEnum.ASSIGNED)) {
					execution = runTimeService.createExecutionQuery()
							.processInstanceId(processInstance.getProcessInstanceId())
							.activityId("WaitForAssingPackageAsAssigned").singleResult();
				} // end if Condition

				runTimeService.signal(execution.getId(), workflowData);
			}

		};
	}

}
