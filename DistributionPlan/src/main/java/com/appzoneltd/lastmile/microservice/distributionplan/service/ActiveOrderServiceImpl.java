package com.appzoneltd.lastmile.microservice.distributionplan.service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleCouchbaseRepo;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.BuildingEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.BuildingRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PackageRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanOrdersRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.ReturnRequestRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.VehicleActiveOrder;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.VehicleOrder;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.ActionOrderDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.ActiveOrderDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.ActiveVehicleOrderDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.Mapper;

@Service
public class ActiveOrderServiceImpl implements ActiveOrderService {

	private final PlanRepository planRepository;
	private final PlanOrdersRepository planOrdersRepository;
	private final RequestHistoryJpaRepository requestHistory;
	private final PickupRequestJpaRepository pickupRequest;
	private final Mapper mapper;
	private final ReturnRequestRepository returnRequestRepository;
	private final ActiveVehicleRepository activeVehicleRepository;
	private final PackageRepository packageRepository;
	private final ActiveVehicleCouchbaseRepo activeVehicleCouchbaseRepo;
	private final PrincipalService principalService;
	private final BuildingRepository buildingRepository;

	@Autowired
	public ActiveOrderServiceImpl(PlanRepository planRepository, RequestHistoryJpaRepository requestHistory,
			PickupRequestJpaRepository pickupRequest, PlanOrdersRepository planOrdersRepository, Mapper mapper,
			ReturnRequestRepository returnRequestRepository, ActiveVehicleCouchbaseRepo activeVehicleCouchbaseRepo,
			ActiveVehicleRepository activeVehicleRepository, PackageRepository packageRepository,
			PrincipalService principalService, BuildingRepository buildingRepository) {
		this.planRepository = planRepository;
		this.requestHistory = requestHistory;
		this.pickupRequest = pickupRequest;
		this.planOrdersRepository = planOrdersRepository;
		this.mapper = mapper;
		this.returnRequestRepository = returnRequestRepository;
		this.activeVehicleCouchbaseRepo = activeVehicleCouchbaseRepo;
		this.activeVehicleRepository = activeVehicleRepository;
		this.packageRepository = packageRepository;
		this.principalService = principalService;
		this.buildingRepository = buildingRepository;
	}

	private Long getTodayPlanId() {
		return planRepository.findTodayPlan();
	}

	public Integer getTotalOrderType(List<VehicleOrder> vehicleOrders, String OrderType) {
		Integer count = 0;

		for (VehicleOrder vehicleOrder : vehicleOrders) {
			if (vehicleOrder.getOrderType().equals(OrderType)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public ActiveOrderDto getActiveOrderDTO(VehicleActiveOrder vehicleActiveOrder) {
		ActiveOrderDto activeOrderDto = new ActiveOrderDto();
		activeOrderDto.setActiveVehicleId(vehicleActiveOrder.getActiveVehicleId());
		activeOrderDto.setDriverName(vehicleActiveOrder.getDriverName());
		activeOrderDto.setDeviceNumber(vehicleActiveOrder.getDeviceNumber());
		activeOrderDto.setDriverRate(vehicleActiveOrder.getDriverRate());
		activeOrderDto.setDriverImageId(vehicleActiveOrder.getDriverImageId());
		activeOrderDto.setVehicleState(vehicleActiveOrder.getVehicleState());
		activeOrderDto.setActiveVehiclePurpose(vehicleActiveOrder.getActiveVehiclePurpose());
		activeOrderDto.setCurrentSourceAddress(vehicleActiveOrder.getActiveVehicleAddress());
		Long buildingId = activeVehicleCouchbaseRepo.getHubIdToActiveVehicle(vehicleActiveOrder.getActiveVehicleId());
		activeOrderDto.setBuildingName(getBuildingName(buildingId));

		Integer totalPickedup = getTotalOrderType(vehicleActiveOrder.getVehicleOrders(), "PICKUP");
		Integer totalDelivery = getTotalOrderType(vehicleActiveOrder.getVehicleOrders(), "DELIVERY");
		Integer totalReturn = getTotalOrderType(vehicleActiveOrder.getVehicleOrders(), "RETURN");
		Integer completedPickup = 0;
		Integer completedDelivery = 0;
		Integer completedReturn = 0;

		int currentIndex = 0;
		for (int index = 0; index < vehicleActiveOrder.getVehicleOrders().size(); index++) {
			String orderType = vehicleActiveOrder.getVehicleOrders().get(index).getOrderType();
			RequestHistoryEntity requestHistoryEntity = requestHistory
					.getAllRequestHistoryWithType(vehicleActiveOrder.getVehicleOrders().get(index).getOrderId() , orderType).get(0);
			
			if (requestHistoryEntity != null) {
				String requestStatus = requestHistoryEntity.getRequestStatus();
				if (requestStatus.equals("PICKEDUP")) {
					completedPickup++;
				} else if (requestStatus.equals("DELIVERED")) {
					completedDelivery++;
				} else if (requestStatus.equals("RETURNED")) {
					completedReturn++;
				} else if (requestStatus.equals("AWAITING_PICKUP")) {
					currentIndex = index;
					break;
				}
			}
		}

		String currentSource = "";
		String currentDestination = "";
		if (vehicleActiveOrder.getVehicleState().equals("ON_ROUTE")) {
			System.out.println("ON_ROUTE");
			System.out.println(vehicleActiveOrder.toString());
			currentDestination = pickupRequest
					.findOne(vehicleActiveOrder.getVehicleOrders().get(currentIndex).getOrderId())
					.getPickupformatedaddress();
			currentSource = vehicleActiveOrder.getLocation();
			System.out.println("currentSource "+currentSource);
			if (currentIndex != 0) {
				// currentSource = pickupRequest
				// .findOne(vehicleActiveOrder.getVehicleOrders().get(currentIndex
				// - 1).getOrderId())
				// .getPickupformatedaddress();

			}
		}

		activeOrderDto.setOrderType(vehicleActiveOrder.getVehicleOrders().get(currentIndex).getOrderType());
		activeOrderDto.setCompletedPickup(completedPickup);
		activeOrderDto.setTotalPickup(totalPickedup);
		activeOrderDto.setCompletedDelivery(completedDelivery);
		activeOrderDto.setTotalDelivery(totalDelivery);
		activeOrderDto.setCurrentDestinationAddress(currentDestination);
		activeOrderDto.setCurrentSourceAddress(currentSource);
		activeOrderDto.setCompletedReturn(completedReturn);
		activeOrderDto.setTotalReturn(totalReturn);
		return activeOrderDto;
	}

	@Override
	public List<ActiveOrderDto> fillActiveOrders(List<VehicleActiveOrder> vehicleActiveOrders) {
		System.out.println("fillActiveOrders");
		List<ActiveOrderDto> activeOrderDtos = new ArrayList<>();
		if(vehicleActiveOrders !=null){
			for (VehicleActiveOrder vehicleActiveOrder : vehicleActiveOrders) {
				System.out.println("vehicleActiveOrder "+vehicleActiveOrder.toString());
				ActiveOrderDto activeOrderDto = getActiveOrderDTO(vehicleActiveOrder);
				activeOrderDtos.add(activeOrderDto);
			}
			Collections.sort(activeOrderDtos, new Comparator<ActiveOrderDto>() {
				@Override
				public int compare(ActiveOrderDto o1, ActiveOrderDto o2) {
					return o1.getActiveVehicleId().compareTo(o2.getActiveVehicleId());
				}
			});

		}
		return activeOrderDtos;
	}

	@Override
	public List<ActiveOrderDto> getAllActiveOrders(Long hubId , Principal principal) {
		List<VehicleActiveOrder> vehicleActiveOrders = new ArrayList<>();
		List<VehicleActiveOrder> onDemandPickupsToActiveVehilce = new ArrayList<>();
		// check if active orders related to building
		boolean activeOrdersRelatedToSuperUser = principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal);

		Long planId = null;
		if (!activeOrdersRelatedToSuperUser) {
			hubId = principalService.getHubIdIfFound(principal);
		}
		if(hubId !=null){
			planId = planRepository.findTodayPlanToHub(hubId);
			// get Ondemand Pickups To Active Vehicle
			onDemandPickupsToActiveVehilce = activeVehicleCouchbaseRepo.getOndemandPickupsToActiveVehicles(hubId);
		}
		if (onDemandPickupsToActiveVehilce.size() > 0 && !onDemandPickupsToActiveVehilce.isEmpty()) {
			for(VehicleActiveOrder vehicleActiveOrder : onDemandPickupsToActiveVehilce){
				System.out.println(vehicleActiveOrder.toString());
			}
			vehicleActiveOrders.addAll(onDemandPickupsToActiveVehilce);
		}
		System.out.println("plan id "+planId);
		System.out.println("hub id "+hubId);
		if (planId != null) {
			List<PlanOrderEntity> planOrderEntities = planOrdersRepository.findByPlanIdGroupByActiveVehicle(planId);
			Collections.sort(planOrderEntities,
					(PlanOrderEntity r1, PlanOrderEntity r2) -> r1.getId().compareTo(r2.getId()));

			List<Long> activeVehicleIds = new ArrayList<>();

			for (PlanOrderEntity planOrderEntity : planOrderEntities) {
				System.out.println("orderId "+planOrderEntity.getOrderId());
				if (!activeVehicleIds.contains(planOrderEntity.getActiveVehicle().getId())) {
					System.out.println("active vehicle Id "+planOrderEntity.getActiveVehicle().getId());
					activeVehicleIds.add(planOrderEntity.getActiveVehicle().getId());
					Boolean workingStatus = 
							activeVehicleCouchbaseRepo.getActivleVehicleWorkingStatus(planOrderEntity.getActiveVehicle().getId());
					if (workingStatus == null) {
						workingStatus = false;
					}
					if (workingStatus) {
						System.out.println("workingStatus "+workingStatus);
						vehicleActiveOrders.addAll(mapper.mapToActiveOrders(planOrderEntities));

					}
				}

			}

		}

		return fillActiveOrders(vehicleActiveOrders);

	}

	@Override
	public ActiveVehicleOrderDto getActionOrderListForScheduledVehicle(Long activeVehicleId) {

		ActiveVehicleOrderDto activeVehicleOrderDto = null;
		List<PlanOrderEntity> planOrderEntities = planOrdersRepository.getActiveVehicleInfo(activeVehicleId);

		PlanOrderEntity planOrderEntity = null;
		if (planOrderEntities.size() > 0) {
			activeVehicleOrderDto = new ActiveVehicleOrderDto();
			planOrderEntity = planOrderEntities.get(0);
			ActiveVehicleEntity activeVehicleEntity = planOrderEntity.getActiveVehicle();

			if (activeVehicleEntity != null) {
				activeVehicleOrderDto = mapper.mapToActiveOrderDto(activeVehicleEntity);

				List<ActionOrderDto> actionOrderDtos = new ArrayList<>();
				actionOrderDtos = getListOrdersForActiveVehicleInPlan(activeVehicleEntity);
				activeVehicleOrderDto.setActionOrderDtos(actionOrderDtos);
				return activeVehicleOrderDto;
			}
		}

		return activeVehicleOrderDto;
	}

	@Override
	public ActiveVehicleOrderDto getActionOrderListForOndemandVehicle(Long activeVehicleId) {

		ActiveVehicleOrderDto activeVehicleOrderDto = null;
		activeVehicleOrderDto = new ActiveVehicleOrderDto();

		ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.findOne(activeVehicleId);

		if (activeVehicleEntity != null) {

			activeVehicleOrderDto = mapper.mapToActiveOrderDto(activeVehicleEntity);

			List<ActionOrderDto> actionOrderDtos = new ArrayList<>();
			actionOrderDtos = getListOrdersForOndemandActiveVehicle(activeVehicleEntity);
			activeVehicleOrderDto.setActionOrderDtos(actionOrderDtos);
			return activeVehicleOrderDto;

		}

		return activeVehicleOrderDto;
	}

	private boolean isDateEqualToday(Date date) {
		Date dateNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		if (sdf.format(dateNow).equals(sdf.format(date)))
			return true;
		return false;
	}

	private List<ActionOrderDto> getListOrdersForActiveVehicleInPlan(ActiveVehicleEntity activeVehicleEntity) {

		List<ActionOrderDto> actionOrderDtos = new ArrayList<>();

		for (PlanOrderEntity planOrder : activeVehicleEntity.getListOfPlanOrders()) {
			if (isDateEqualToday(planOrder.getCreated())) {
				PickupRequestEntity pickupRequestEntity = pickupRequest.findOne(planOrder.getOrderId());
				ActionOrderDto actionOrderDto = new ActionOrderDto();
				actionOrderDto.setOrderId(planOrder.getOrderId());
				actionOrderDto.setOrderType(planOrder.getOrderType());
				actionOrderDto.setPackageType(planOrder.getPackageEntity().getPackageType().getPackagetype());
				actionOrderDto.setOrderTimeFrom(pickupRequestEntity.getTimeFrom());
				actionOrderDto.setOrderTimeTo(pickupRequestEntity.getTimeTo());
				if (planOrder.getOrderType().equals("PICKUP")) {
					actionOrderDto.setAddress(pickupRequestEntity.getPickupformatedaddress());
				} else if (planOrder.getOrderType().equals("DELIVERY")) {
					actionOrderDto.setAddress(pickupRequestEntity.getRecipientformatedaddress());
				}

				else if (planOrder.getOrderType().equals("RETURN")) {
					actionOrderDto.setAddress(
							returnRequestRepository.findOne(planOrder.getOrderId()).getReturnFormatedAddress());
				}

				if (!isOrderFinished(planOrder.getOrderId())) {
					actionOrderDtos.add(actionOrderDto);
				}
			}
		}

		return actionOrderDtos;

	}

	private List<ActionOrderDto> getListOrdersForOndemandActiveVehicle(ActiveVehicleEntity activeVehicleEntity) {

		List<ActionOrderDto> actionOrderDtos = new ArrayList<>();
		List<VehicleOrder> vehicleOrders = activeVehicleCouchbaseRepo.getVehicleJobOrder(activeVehicleEntity.getId());

		for (VehicleOrder vehicleOrder : vehicleOrders) {

			PickupRequestEntity pickupRequestEntity = pickupRequest.findOne(vehicleOrder.getOrderId());

			ActionOrderDto actionOrderDto = new ActionOrderDto();
			actionOrderDto.setOrderId(vehicleOrder.getOrderId());
			actionOrderDto.setOrderType(vehicleOrder.getOrderType());
			actionOrderDto.setPackageType(getPackageTypeForOrder(vehicleOrder.getOrderId()));
			actionOrderDto.setOrderTimeFrom(pickupRequestEntity.getTimeFrom());
			actionOrderDto.setOrderTimeTo(pickupRequestEntity.getTimeTo());
			if (vehicleOrder.getOrderType().equals("PICKUP")) {
				actionOrderDto.setAddress(pickupRequestEntity.getPickupformatedaddress());
			} else if (vehicleOrder.getOrderType().equals("DELIVERY")) {
				actionOrderDto.setAddress(pickupRequestEntity.getRecipientformatedaddress());
			}

			else if (vehicleOrder.getOrderType().equals("RETURN")) {
				actionOrderDto.setAddress(
						returnRequestRepository.findOne(vehicleOrder.getOrderId()).getReturnFormatedAddress());
			}

			if (!isOrderFinished(vehicleOrder.getOrderId())) {
				actionOrderDtos.add(actionOrderDto);
			}

		}

		return actionOrderDtos;

	}

	private boolean isOrderFinished(Long orderId) {
		boolean finished = false;
		RequestHistoryEntity requestHistoryEntity = requestHistory.getAllRequestHistory(orderId).get(0);
		if (requestHistoryEntity.getRequestStatus().equals("PICKEDUP")
				|| requestHistoryEntity.getRequestStatus().equals("DELIVERED")) {
			finished = true;
		}
		return finished;
	}

	private String getPackageTypeForOrder(Long orderId) {
		Long packageId = requestHistory.getAllRequestHistory(orderId).get(0).getPackageId();
		String packageType = packageRepository.findOne(packageId).getPackageType().getPackagetype();
		return packageType;

	}

	@Override
	public boolean chkActiveVehicleRelatedToTodayPlan(Long activeVehicleId) {
		boolean chkActiveVehicleRelatedToTodayPlan = false;
		List<PlanOrderEntity> planOrderEntities = planOrdersRepository.getActiveVehicleInfo(activeVehicleId);
		if (planOrderEntities.size() > 0) {
			chkActiveVehicleRelatedToTodayPlan = true;
		}
		return chkActiveVehicleRelatedToTodayPlan;

	}

	private String getBuildingName(Long buildingId) {
		String buildingName = null;
		BuildingEntity buildingEntity = buildingRepository.findOne(buildingId);
		if (buildingEntity != null) {
			buildingName = buildingEntity.getBuildingname();
		}
		return buildingName;

	}

}
