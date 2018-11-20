//package com.appzoneltd.lastmile.microservice.manualdistribution.service.automaticservice;
//
//import java.util.Date;
//
//import com.appzoneltd.lastmile.microservice.manualdistribution.dao.*;
//import com.appzoneltd.lastmile.microservice.manualdistribution.entity.*;
//import com.appzoneltd.lastmile.microservice.utility.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by hashish on 4/9/2017.
// */
//@Service
//public class SavePlanTmpToPlanImpl implements SavePlanTmpToPlan {
//
//	@Autowired
//	PlanTmpRepo planTmpRepo;
//	@Autowired
//	PlanRepo planRepo;
//	@Autowired
//	SavePlanOrderTmpRepo savePlanOrderTmpRepo;
//	@Autowired
//	SavePlanOrderRepo savePlanOrderRepo;
//	@Autowired
//	PickupJobOrderRepo pickupJobOrderRepo;
//	@Autowired
//	DeliveryJobOrderRepo deliveryJobOrderRepo;
//	@Autowired
//	RequestHistoryJpaRepository requestHistoryJpaRepository;
//
//	private PlanEntityTmp getPlan() {
//		List<PlanEntityTmp> planEntityTmps = (List<PlanEntityTmp>) planTmpRepo.findAll();
//		if (!planEntityTmps.isEmpty()) {
//			PlanEntityTmp planEntityTmp = planEntityTmps.get(0);
//			return planEntityTmp;
//		}
//		return null;
//	}
//
//	private Long SaveOriginalPlan(PlanEntityTmp planEntityTmp, List<SavePlanOrderEntityWithStatus> planDetails) {
//		PlanEntity planEntity = planTmpEntityToPlanEntity(planEntityTmp);
//		List<SavePlanOrderEntity> savePlanOrderEntities = savePlanToSavePlanStatus(planDetails);
//		planRepo.save(planEntity);
//		for (SavePlanOrderEntity savePlanOrderEntity : savePlanOrderEntities) {
//			savePlanOrderRepo.save(savePlanOrderEntity);
//		}
//
//		savePlanOrderTmpRepo.deleteAll();
//		planTmpRepo.deleteAll();
//		Long id = planRepo.findLatest().get(0).getId();
//		return id;
//	}
//
//	private PlanEntity planTmpEntityToPlanEntity(PlanEntityTmp planEntityTmp) {
//		PlanEntity planEntity = new PlanEntity();
//		planEntity.setId(planEntityTmp.getId());
//		return planEntity;
//	}
//
//	private List<SavePlanOrderEntity> savePlanToSavePlanStatus(
//			List<SavePlanOrderEntityWithStatus> planDetailsWithStatus) {
//		List<SavePlanOrderEntity> planDetails = new ArrayList<>();
//		Long saveId=Utils.generateUUID();
//		for (SavePlanOrderEntityWithStatus planItemWithStatus : planDetailsWithStatus) {
//			SavePlanOrderEntity savePlanOrderEntity = new SavePlanOrderEntity();
//			if (!("FAIL".equalsIgnoreCase(planItemWithStatus.getStatus()))) {
//				savePlanOrderEntity.setId(saveId);
//				savePlanOrderEntity.setActiveVehicleId(planItemWithStatus.getActiveVehicleId());
//				savePlanOrderEntity.setArrivalTime(planItemWithStatus.getArrivalTime());
//				savePlanOrderEntity.setDepartureTime(planItemWithStatus.getDepartureTime());
//				savePlanOrderEntity.setEstimatedTimeForArrival(planItemWithStatus.getEstimatedTimeForArrival());
//				savePlanOrderEntity.setJobOrderType(planItemWithStatus.getJobOrderType());
//				savePlanOrderEntity.setOrderId(planItemWithStatus.getOrderId());
//				savePlanOrderEntity.setPackageId(planItemWithStatus.getPackageId());
//				savePlanOrderEntity.setPlanId(planItemWithStatus.getPlanId());
//				savePlanOrderEntity.setPriorty(planItemWithStatus.getPriorty());
//				planDetails.add(savePlanOrderEntity);
//				saveId++;
//			} else {
//
//				if ("PICKUP".equalsIgnoreCase(planItemWithStatus.getJobOrderType())) {
//					updatePickupToActionNeeded(planItemWithStatus);
//				}
//				if ("DELIVERY".equalsIgnoreCase(planItemWithStatus.getJobOrderType())) {
//					updateDeliveryToActionNeeded(planItemWithStatus);
//				}
//			}
//		}
//
//		System.out.println("***************************** H555555555555555555555555555555555555555555 PLAN DETAILS "
//				+ planDetails.toString());
//		return planDetails;
//	}
//
//	private PickupJobOrder updatePickupToActionNeeded(SavePlanOrderEntityWithStatus planItemWithStatus) {
//		PickupJobOrder pickupJobOrder = pickupJobOrderRepo.findOne(planItemWithStatus.getOrderId());
//		if (pickupJobOrder != null) {
//			pickupJobOrder.setJobStatus("ACTION_NEEDED");
//			pickupJobOrderRepo.save(pickupJobOrder);
//			updateHistory(pickupJobOrder);
//		}
//		return pickupJobOrder;
//	}
//
//	private DeliveryJobOrder updateDeliveryToActionNeeded(SavePlanOrderEntityWithStatus planItemWithStatus) {
//		DeliveryJobOrder deliveryJobOrder = deliveryJobOrderRepo.findOne(planItemWithStatus.getOrderId());
//		if (deliveryJobOrder != null) {
//			deliveryJobOrder.setJobStatus("ACTION_NEEDED");
//			deliveryJobOrderRepo.save(deliveryJobOrder);
//			updateHistory(deliveryJobOrder);
//		}
//		return deliveryJobOrder;
//	}
//
//	private RequestHistoryEntity updateHistory(JobOrder jobOrder) {
//		RequestHistoryEntity requestHistoryEntity = requestHistoryJpaRepository
//				.getAllRequestHistory(jobOrder.getJobOrderId()).get(0);
//		if (requestHistoryEntity != null) {
//			RequestHistoryEntity requestHistory = new RequestHistoryEntity();
//			requestHistory.setCreated(new Date());
//			requestHistory.setRequestId(requestHistoryEntity.getRequestId());
//			requestHistory.setRequestHistoryId(Utils.generateUUID());
//			requestHistory.setPackageId(requestHistoryEntity.getPackageId());
//			requestHistory.setRequestType(requestHistoryEntity.getRequestType());
//			requestHistory.setRequestStatus("ACTION_NEEDED");
//			requestHistoryJpaRepository.save(requestHistory);
//		}
//		return requestHistoryEntity;
//	}
//
//	@Override
//	public Long save() {
//		PlanEntityTmp planEntityTmp = getPlan();
//		if (planEntityTmp != null) {
//			List<SavePlanOrderEntityWithStatus> planDetails = savePlanOrderTmpRepo
//					.getPlanDetailsByPlanId(planEntityTmp.getId());
//			return SaveOriginalPlan(planEntityTmp, planDetails);
//		}
//		return null;
//	}
//}
