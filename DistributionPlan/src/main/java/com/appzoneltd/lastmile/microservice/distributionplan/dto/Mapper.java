package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleCouchbaseRepo;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanOrderEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanOrderTmpEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.ReturnRequestRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.UsersEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.UsersRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.VehicleActiveOrder;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.VehicleOrder;
import com.appzoneltd.lastmile.microservice.utility.Utils;

/**
 * Created by alaa.nabil on 2/15/2017.
 */
@Component
public final class Mapper {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ActiveVehicleCouchbaseRepo activeVehicleCouchbaseRepo;

	@Autowired
	private PickupRequestJpaRepository pickupRequestJpaRepository;

	@Autowired
	private DeliveryRequestRepository deliveryRequestRepository;

	@Autowired
	private ReturnRequestRepository returnRequestRepository;

	public final PlanEntity mapToPlanEntity(Plan plan) {
		return new PlanEntity(Utils.generateUUID(), plan.getCreated());
	}

	public final Plan mapToPlanDto(PlanEntity planEntity) {
		return new Plan(planEntity.getId(), planEntity.getCreated());
	}

	public final List<PlanOrder> mapToPlanOrders(List<PlanOrderEntity> planOrderEntities) {
		if (planOrderEntities == null) {
			return null;
		}
		List<PlanOrderEntity> entities = null;
		Map<ActiveVehicleEntity, List<PlanOrderEntity>> orders = new HashMap<>();

		for (PlanOrderEntity planOrderEntity : planOrderEntities) {
			if (orders.containsKey(planOrderEntity.getActiveVehicle()))
				orders.get(planOrderEntity.getActiveVehicle()).add(planOrderEntity);

			else {
				entities = new ArrayList<>();
				entities.add(planOrderEntity);
				orders.put(planOrderEntity.getActiveVehicle(), entities);
			}

		}
		return buildOrder(orders);
	}

	private List<PlanOrder> buildOrder(Map<ActiveVehicleEntity, List<PlanOrderEntity>> planOrdersMap) {
		List<PlanOrder> planOrders = new ArrayList<>();
		for (Map.Entry<ActiveVehicleEntity, List<PlanOrderEntity>> entry : planOrdersMap.entrySet()) {
			List<Order> jobs = new ArrayList<>();
			for (PlanOrderEntity planOrderEntity : entry.getValue()) {
				String reasonName = null;
				if (planOrderEntity.getRejectionReason() != null) {
					reasonName = planOrderEntity.getRejectionReason().getReason();
				}

				jobs.add(new Order(planOrderEntity.getOrderId(),
						planOrderEntity.getPackageEntity().getActualweight().toPlainString(),
						planOrderEntity.getPackageEntity().getPackageType().getPackagetype(),
						planOrderEntity.getPackageEntity().getDescription(), planOrderEntity.getDriverResponse(),
						reasonName));
			}
			planOrders.add(new PlanOrder(entry.getKey().getId(),
					usersRepository.findOne(entry.getKey().getDriver().getId()).getUsername(),
					entry.getKey().getWorkShift().getDateFrom(), entry.getKey().getWorkShift().getDateTo(),
					entry.getKey().getVehicle().getVehicleType().getType(), jobs));
		}
		return planOrders;
	}

	public List<VehicleActiveOrder> mapToActiveOrders(List<PlanOrderEntity> planOrderEntities) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>  MAP ACTIVE ORDER");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		if (planOrderEntities == null) {
			return null;
		}
		List<PlanOrderEntity> entities = null;
		Map<ActiveVehicleEntity, List<PlanOrderEntity>> orders = new HashMap<>();

		for (PlanOrderEntity planOrderEntity : planOrderEntities) {
			if (orders.containsKey(planOrderEntity.getActiveVehicle()))
				orders.get(planOrderEntity.getActiveVehicle()).add(planOrderEntity);

			else {
				entities = new ArrayList<>();
				entities.add(planOrderEntity);
				orders.put(planOrderEntity.getActiveVehicle(), entities);
			}

		}
		return buildActiveOrder(orders);
	}

	private List<VehicleActiveOrder> buildActiveOrder(Map<ActiveVehicleEntity, List<PlanOrderEntity>> planOrdersMap) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>  BUILD ACTIVE ORDER");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		List<VehicleActiveOrder> activeOrders = new ArrayList<>();
		for (Map.Entry<ActiveVehicleEntity, List<PlanOrderEntity>> entry : planOrdersMap.entrySet()) {
			List<VehicleOrder> jobs = new ArrayList<>();
			for (PlanOrderEntity planOrderEntity : entry.getValue()) {
				VehicleOrder vehicleOrder = new VehicleOrder();
				vehicleOrder.setOrderId(planOrderEntity.getOrderId());
				vehicleOrder.setOrderType(planOrderEntity.getOrderType());
				vehicleOrder.setPriorty(planOrderEntity.getPriority());
				jobs.add(vehicleOrder);
			}
			VehicleActiveOrder activeOrder = new VehicleActiveOrder();
			UsersEntity userEntity = usersRepository.findOne(entry.getKey().getDriver().getId());

			activeOrder.setActiveVehicleId(entry.getKey().getId());
			activeOrder.setDriverName(userEntity.getUsername());
			activeOrder.setDeviceNumber(entry.getKey().getDevices().getPhonenumber());
			activeOrder.setDriverImageId(userEntity.getImageId());
			if(entry.getKey().getDriver() !=null && entry.getKey().getDriver().getRating() !=null){
				activeOrder.setDriverRate(entry.getKey().getDriver().getRating().toPlainString());
			}
			
			activeOrder.setActiveVehiclePurpose(activeVehicleCouchbaseRepo.getActiveVehiclePurpose(entry.getKey().getId()));
			activeOrder.setVehicleState(
					activeVehicleCouchbaseRepo.getActiveVehicleStatus(entry.getKey().getId()).getVehicleState());
			System.out.println("jobs.size() "+jobs.size());
			if(jobs.size() !=0){
				activeOrder.setLocation(activeVehicleCouchbaseRepo.getFirstOrderLocation(entry.getKey().getId()));
				System.out.println("active order location "+activeVehicleCouchbaseRepo.getFirstOrderLocation(entry.getKey().getId()));
			}
			activeOrder.setVehicleOrders(jobs);
			
			// Adding to List
			activeOrders.add(activeOrder);
		}
		return activeOrders;
	}

	public List<PlanDetailsDto> mapToPlanDetails(List<PlanOrderEntity> planOrderEntities) {
		if (planOrderEntities == null) {
			return null;
		}
		List<PlanOrderEntity> entities = null;
		Map<ActiveVehicleEntity, List<PlanOrderEntity>> orders = new HashMap<>();

		for (PlanOrderEntity planOrderEntity : planOrderEntities) {
			if (orders.containsKey(planOrderEntity.getActiveVehicle()))
				orders.get(planOrderEntity.getActiveVehicle()).add(planOrderEntity);

			else {
				entities = new ArrayList<>();
				entities.add(planOrderEntity);
				orders.put(planOrderEntity.getActiveVehicle(), entities);
			}

		}
		return buildPlanDetails(orders);
	}

	private List<PlanDetailsDto> buildPlanDetails(Map<ActiveVehicleEntity, List<PlanOrderEntity>> planOrdersMap) {
		List<PlanDetailsDto> planDetailsDtos = new ArrayList<>();
		for (Map.Entry<ActiveVehicleEntity, List<PlanOrderEntity>> entry : planOrdersMap.entrySet()) {
			List<JobOrderDto> jobs = new ArrayList<>();
			for (PlanOrderEntity planOrderEntity : entry.getValue()) {
				Long orderId = planOrderEntity.getOrderId();
				String orderType = planOrderEntity.getOrderType();
				PickupDeliveryInfo pickupDeliveryInfo = getOrderInfo(orderId, orderType);

				JobOrderDto jobOrderDto = new JobOrderDto();
				jobOrderDto.setPackageType(planOrderEntity.getPackageEntity().getPackageType().getPackagetype());
				jobOrderDto.setActualWeight(planOrderEntity.getPackageEntity().getActualweight());
				jobOrderDto.setArrivalTime(convertDateToLong(planOrderEntity.getArrivalTime()));
				jobOrderDto.setDepartureTime(convertDateToLong(planOrderEntity.getDepartureTime()));
				jobOrderDto.setJobAddress(pickupDeliveryInfo.getFormattedAddress());
				jobOrderDto.setJobType(planOrderEntity.getOrderType());
				jobOrderDto.setPriority(planOrderEntity.getPriority());
				jobOrderDto.setTimeFrom(pickupDeliveryInfo.getTimeFrom());
				jobOrderDto.setTimeTo(pickupDeliveryInfo.getTimeTo());
				jobOrderDto.setTimeTakenRoutingEngineInText(planOrderEntity.getEta());
				jobOrderDto.setStatus(planOrderEntity.getStatus());

				jobs.add(jobOrderDto);
			}
			PlanDetailsDto planDetailsDto = new PlanDetailsDto();
			UsersEntity userEntity = usersRepository.findOne(entry.getKey().getDriver().getId());
			planDetailsDto.setActiveVehicleStatus(entry.getKey().getActive());
			planDetailsDto.setActiveVehicleType(entry.getKey().getVehicle().getVehicleType().getType());
			planDetailsDto.setActiveVehicleWorkShiftFrom(entry.getKey().getWorkShift().getDateFrom());
			planDetailsDto.setActiveVehicleWorkShiftTo(entry.getKey().getWorkShift().getDateTo());
			planDetailsDto.setDriverName(userEntity.getUsername());

			planDetailsDto.setJobOrders(jobs);

			// Adding to List
			planDetailsDtos.add(planDetailsDto);
		}
		return planDetailsDtos;
	}

	private Long convertDateToLong(Date date){
		try {
			String str = date+"";
			DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			Date newDate = formatter.parse(str);
			long timestamp = newDate.getTime();
			return timestamp;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public List<PlanDetailsDto> mapToTmpPlanDetails(List<PlanOrderTmpEntity> planOrderTmpEntities) {
		if (planOrderTmpEntities == null) {
			return null;
		}
		List<PlanOrderTmpEntity> entities = null;
		Map<ActiveVehicleEntity, List<PlanOrderTmpEntity>> orders = new HashMap<>();

		for (PlanOrderTmpEntity planOrderTmpEntity : planOrderTmpEntities) {
			if (orders.containsKey(planOrderTmpEntity.getActiveVehicle()))
				orders.get(planOrderTmpEntity.getActiveVehicle()).add(planOrderTmpEntity);

			else {
				entities = new ArrayList<>();
				entities.add(planOrderTmpEntity);
				orders.put(planOrderTmpEntity.getActiveVehicle(), entities);
			}

		}
		return buildTmpPlanDetails(orders);
	}

	private List<PlanDetailsDto> buildTmpPlanDetails(Map<ActiveVehicleEntity, List<PlanOrderTmpEntity>> planOrdersMap) {
		List<PlanDetailsDto> planDetailsDtos = new ArrayList<>();
		for (Map.Entry<ActiveVehicleEntity, List<PlanOrderTmpEntity>> entry : planOrdersMap.entrySet()) {
			List<JobOrderDto> jobs = new ArrayList<>();
			for (PlanOrderTmpEntity planOrderTmpEntity : entry.getValue()) {
				Long orderId = planOrderTmpEntity.getOrderId();
				String orderType = planOrderTmpEntity.getOrderType();
				PickupDeliveryInfo pickupDeliveryInfo = getOrderInfo(orderId, orderType);
				JobOrderDto jobOrderTmpDto = new JobOrderDto();
				jobOrderTmpDto.setActualWeight(planOrderTmpEntity.getPackageEntity().getActualweight());
				jobOrderTmpDto.setArrivalTime(convertDateToLong(planOrderTmpEntity.getArrivalTime()));
				jobOrderTmpDto.setDepartureTime(convertDateToLong(planOrderTmpEntity.getDepartureTime()));
				jobOrderTmpDto.setJobAddress(pickupDeliveryInfo.getFormattedAddress());
				jobOrderTmpDto.setJobType(planOrderTmpEntity.getOrderType());
				jobOrderTmpDto.setPriority(planOrderTmpEntity.getPriority());
				jobOrderTmpDto.setTimeFrom(pickupDeliveryInfo.getTimeFrom());
				jobOrderTmpDto.setTimeTo(pickupDeliveryInfo.getTimeTo());
				jobOrderTmpDto.setTimeTakenRoutingEngineInText(planOrderTmpEntity.getEta());
				jobOrderTmpDto.setStatus(planOrderTmpEntity.getStatus());
				jobs.add(jobOrderTmpDto);
			}
			PlanDetailsDto planDetailsDto = new PlanDetailsDto();
			UsersEntity userEntity = usersRepository.findOne(entry.getKey().getDriver().getId());
			planDetailsDto.setActiveVehicleStatus(entry.getKey().getActive());
			planDetailsDto.setActiveVehicleType(entry.getKey().getVehicle().getVehicleType().getType());
			planDetailsDto.setActiveVehicleWorkShiftFrom(entry.getKey().getWorkShift().getDateFrom());
			planDetailsDto.setActiveVehicleWorkShiftTo(entry.getKey().getWorkShift().getDateTo());
			planDetailsDto.setDriverName(userEntity.getUsername());

			planDetailsDto.setJobOrders(jobs);

			// Adding to List
			planDetailsDtos.add(planDetailsDto);
		}
		return planDetailsDtos;
	}

	private PickupDeliveryInfo getOrderInfo(Long orderId, String orderType) {
		PickupDeliveryInfo pickupDeliveryInfo = new PickupDeliveryInfo();
		if ("PICKUP".equalsIgnoreCase(orderType)) {
			PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findOne(orderId);
			pickupDeliveryInfo.setTimeFrom(pickupRequestEntity.getTimeFrom());
			pickupDeliveryInfo.setTimeTo(pickupRequestEntity.getTimeTo());
			pickupDeliveryInfo.setFormattedAddress(pickupRequestEntity.getPickupformatedaddress());
		}

		else if ("DELIVERY".equalsIgnoreCase(orderType)) {
			DeliveryRequestEntity deliveryRequestEntity = deliveryRequestRepository.findOne(orderId);
			deliveryRequestEntity.setTimeFrom(deliveryRequestEntity.getTimeFrom());
			deliveryRequestEntity.setTimeTo(deliveryRequestEntity.getTimeTo());
			deliveryRequestEntity.setRecipientformatedaddress(deliveryRequestEntity.getRecipientformatedaddress());

		}

		else if ("RETURN".equalsIgnoreCase(orderType)) {
			ReturnRequestEntity returnRequestEntity = returnRequestRepository.findOne(orderId);
			pickupDeliveryInfo.setTimeFrom(returnRequestEntity.getTimeFrom());
			pickupDeliveryInfo.setTimeTo(returnRequestEntity.getTimeTo());
			pickupDeliveryInfo.setFormattedAddress(returnRequestEntity.getRecipientformatedaddress());

		}

		return pickupDeliveryInfo;
	}
	
	
	
	public ActiveVehicleOrderDto mapToActiveOrderDto(ActiveVehicleEntity activeVehicleEntity) {
		ActiveVehicleOrderDto activeVehicleOrderDto = new ActiveVehicleOrderDto();

		UsersEntity usersEntity = usersRepository.findOne(activeVehicleEntity.getDriver().getId());
		activeVehicleOrderDto.setDriverName(usersEntity.getUsername());
		activeVehicleOrderDto.setDeviceNumber(activeVehicleEntity.getDevices().getPhonenumber());
		activeVehicleOrderDto.setDriverImg(usersEntity.getImageId());
		activeVehicleOrderDto.setLicenseId(activeVehicleEntity.getDriver().getDrivingLicenseId());
		activeVehicleOrderDto.setVehicleId(activeVehicleEntity.getVehicle().getVehicleId());
		activeVehicleOrderDto.setBuildingId(activeVehicleEntity.getVehicle().getBuildingId());
		activeVehicleOrderDto.setVehicleBrand(activeVehicleEntity.getVehicle().getBrand());
		activeVehicleOrderDto.setVehicleColor(activeVehicleEntity.getVehicle().getColor());
		activeVehicleOrderDto.setVehicleModel(activeVehicleEntity.getVehicle().getModel());
		activeVehicleOrderDto.setVehiclePlateNo(activeVehicleEntity.getVehicle().getPlate());


		return activeVehicleOrderDto;

	}
}
