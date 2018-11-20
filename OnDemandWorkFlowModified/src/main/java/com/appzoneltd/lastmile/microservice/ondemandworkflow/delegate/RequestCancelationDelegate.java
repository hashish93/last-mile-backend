package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.CancelReasonEnum;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.ChangePackageStatusEnum;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.PackageStatusModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.RequestService;

@Service
public class RequestCancelationDelegate implements JavaDelegate {

	@Autowired
	private RequestService requestService;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long packageId = (Long) execution.getVariable("packageId");
		Long requestId = (Long) execution.getVariable("requestId");

		PackageStatusModel packageStatusModel = new PackageStatusModel();
		packageStatusModel.setPackageId(packageId);
		packageStatusModel.setRequestId(requestId);
		packageStatusModel.setStatus(ChangePackageStatusEnum.CANCELED.getPackageStatus());
		// CHANGE STATUS
		requestService.changePackageStatus(packageStatusModel);

		PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
		pickupRequestEntity.setCancellationReason(CancelReasonEnum.NO_COVERAGE.name());
		if (pickupRequestEntity != null) {
			pickupRequestRepository.save(pickupRequestEntity);
		}
		// workFlowProducer.sendMessage("changeRequestStatusCouchbaseRequest",
		// mapper.writeValueAsString(workflowPackageStatus));

	}

}