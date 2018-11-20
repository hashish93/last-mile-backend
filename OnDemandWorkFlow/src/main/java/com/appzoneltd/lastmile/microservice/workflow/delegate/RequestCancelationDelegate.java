package com.appzoneltd.lastmile.microservice.workflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.CancelReasonEnum;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RequestCancelationDelegate implements JavaDelegate {

	@Autowired
	private WorkFlowProducer workFlowProducer;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(">> Cancellation Reason");
		Long packageId = (Long) execution.getVariable("packageId");
		Long requestId = (Long) execution.getVariable("requestId");

		WorkflowPackageStatus workflowPackageStatus = new WorkflowPackageStatus();
		workflowPackageStatus.setRequestId(requestId);
		workflowPackageStatus.setPackageId(packageId);
		workflowPackageStatus.setStatus(ChangePackageStatusEnum.CANCELED);
		
		String activityName = execution.getCurrentActivityName();		
		if (activityName != null) {
			String cancelReason = activityName.substring(activityName.indexOf('"') + 1, activityName.lastIndexOf('"')).trim();
			
			if(cancelReason.equals("No_Coverage")){
				workflowPackageStatus.setCancelReason(CancelReasonEnum.NO_COVERAGE.name());
			}
			
			workFlowProducer.sendMessage("ChangePackageStatusRequest",
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageStatus));

			workFlowProducer.sendMessage("changeRequestStatusCouchbaseRequest",
					mapper.writeValueAsString(workflowPackageStatus));

		}

		

		

	}

}