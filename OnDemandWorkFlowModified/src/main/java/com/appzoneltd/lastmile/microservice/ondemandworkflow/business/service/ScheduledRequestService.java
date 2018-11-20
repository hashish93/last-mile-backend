package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.ActiveVehicleJpaRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PlanJpaRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PlanOrderJpaRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PlanEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ResceduledWorkFlowBase;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ScheduledRequestService {

	@Autowired
	private PickupRequestRepository pickupRequestRepository;
	
	@Autowired
	private PlanJpaRepository planRepository;
	
	@Autowired
	private PlanOrderJpaRepository planOrderRepository;
	
	@Autowired
	private ActiveVehicleJpaRepository activeVehicleRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private OnDemandNotificationService onDemandNotificationService;
	
	public void pushNotificationToDriverOnRequestResceduled(ResceduledWorkFlowBase resceduledWorkFlowBase) throws JsonProcessingException{
		Long hubId = null;
		Long driverId = null;
		if(resceduledWorkFlowBase.getRequestId() !=null){
			PickupRequestEntity pickupRequestEntity = pickupRequestRepository.findByPickupRequestId(resceduledWorkFlowBase.getRequestId());
			if(pickupRequestEntity !=null){
				hubId = pickupRequestEntity.getHubId();
			}
		}
		
		
		if(hubId !=null){ 
			PlanEntity planEntity = planRepository.findTodayPlanToHub(hubId);
			if(planEntity != null){
				List<PlanOrderEntity> planOrderEntities = planOrderRepository.findPlanOrderByRequestIdAndPlanId(planEntity.getId(), resceduledWorkFlowBase.getRequestId());
				if(planOrderEntities !=null && planOrderEntities.size() > 0){
					PlanOrderEntity planOrderEntity =planOrderEntities.get(0);
					planOrderEntity.setExcluded(true);
					planOrderRepository.save(planOrderEntity);
					// TODO send push notification
					ActiveVehicleEntity activeVehicleEntity =  activeVehicleRepository.findOne(planOrderEntity.getActiveVehicleId());
					if(activeVehicleEntity !=null){
						if(activeVehicleEntity.getDriver() !=null){
							driverId = activeVehicleEntity.getDriver().getId();
						}
					}
				}
			}
		}
		
		
		if(driverId !=null){
			WorkflowNotificationModel workflowNotificationModel = new WorkflowNotificationModel();
			workflowNotificationModel.setDriverId(driverId);
			workflowNotificationModel.setRequestId(resceduledWorkFlowBase.getRequestId());
			workflowNotificationModel.setPackageId(resceduledWorkFlowBase.getPackageId());
			onDemandNotificationService.sendRescheduledNotificationToDriver(workflowNotificationModel);
		}
	}

}
