package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.service.RequestServingAreaService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.OnDemandWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ActionNeededBase;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PickUpHubDelegate implements JavaDelegate {

	@Autowired
	private RequestServingAreaService requestServingAreaService;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private RuntimeService runTimeService;
	
	@Autowired
	private OnDemandWorkFlowProducer onDemandWorkFlowProducer;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		MyPrinter.workflowStep("3- Getting PickUp Hub Location According to PackageID");

		Long requestId = (Long) execution.getVariable("requestId");
		Long packageId = (Long) execution.getVariable("packageId");
		String requestType = (String) execution.getVariable("requestType");
		
		PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findOne(requestId);
		if (pickupRequestEntity != null) {
			Long hubId = requestServingAreaService.gettingPickRequestServingAreaHubBuilding(
					pickupRequestEntity.getPickuplatitude(), pickupRequestEntity.getPickuplongitude());

			boolean relatedToHub = false;

			if (hubId != null) {
				MyPrinter.print("HUB ID ", hubId.toString());
				pickupRequestEntity.setHubId(hubId);
				pickupRequestRepository.save(pickupRequestEntity);
				relatedToHub = true;

			}
			// Complete OnDemand Request
			Map<String, Object> workflowData = new HashMap<String, Object>();
			workflowData.put("packageId", packageId);
			workflowData.put("requestId", requestId);
			workflowData.put("adminAssign", false);
			workflowData.put("relatedToHub", relatedToHub);

			if ("ON-DEMAND".equalsIgnoreCase(requestType)) {
				MyPrinter.workflowStep("START ON_DEMAND ");
				// START Driver Response TimeOut 
				ActionNeededBase actionNeededBase=new ActionNeededBase();
				actionNeededBase.setPackageId(packageId);
				actionNeededBase.setSwitched(true);
				MyPrinter.print("PickUpHubDelegate", "actionNeededBase "+actionNeededBase.toString());
					onDemandWorkFlowProducer.sendMessage("AUTOMATIC_ACTION_NEEDED",
							mapper.writeValueAsString(actionNeededBase));
				runTimeService.startProcessInstanceByMessage("startNearByVehicleModule", workflowData);
			} else if ("SCHEDULED".equalsIgnoreCase(requestType)) {
				execution.setVariable("relatedToHub", relatedToHub);
			}

		}

	}

}
