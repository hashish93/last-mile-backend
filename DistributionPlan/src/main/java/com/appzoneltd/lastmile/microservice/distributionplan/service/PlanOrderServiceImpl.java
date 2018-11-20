package com.appzoneltd.lastmile.microservice.distributionplan.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanOrdersRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.UsersEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dao.UsersRepository;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.Mapper;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.PlanOrder;

/**
 * Created by alaa.nabil on 2/15/2017.
 */
@Service
public class PlanOrderServiceImpl implements PlanOrderService {

	private final PlanOrdersRepository planOrdersRepository;
	private final PlanRepository planRepository;
	private final Mapper mapper;
	private final UsersRepository usersRepository;
	private final PrincipalService principalService;

	@Autowired
	public PlanOrderServiceImpl(PlanOrdersRepository planOrdersRepository, PlanRepository planRepository, Mapper mapper,
			UsersRepository usersRepository, PrincipalService principalService) {
		this.planOrdersRepository = planOrdersRepository;
		this.mapper = mapper;
		this.planRepository = planRepository;
		this.usersRepository = usersRepository;
		this.principalService = principalService;

	}

	@Override
	public List<PlanOrder> getOrdersByPlan(Principal principal) {
		if (getLatestPlanId(principal) == null)
			return null;
		return getOrdersByPlanId(getLatestPlanId(principal));
	}

	@Override
	public List<PlanOrder> getOrdersByPlanId(long planId) {

		return mapper.mapToPlanOrders(planOrdersRepository.findByPlanIdGroupByActiveVehicle(planId));
	}

	private Long getLatestPlanId(Principal principal) {

		Long latestPlanId = null;

		if (principalService.isSuperAdmin(principal)) {
			List<PlanEntity> plans = planRepository.findLatest();
			if (plans == null || plans.isEmpty())
				return null;
			latestPlanId = plans.get(0).getId();

		}

		else {

			List<Long> hubs = principalService.getHubs(principal);
			List<PlanEntity> planEntities = planRepository.findLatestToHub(hubs);
			if(planEntities == null || planEntities.isEmpty()){
				return null;
			}
			latestPlanId = planRepository.findLatestToHub(hubs).get(0).getId();
		}
		return latestPlanId;
	}

	private Long getLatestPlanIdAndHubs(Principal principal,List<Long> hubs) {

		Long latestPlanId = null;
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("list of hubs "+hubs.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		List<PlanEntity> planEntities = planRepository.findLatestToHub(hubs);
		if(planEntities == null || planEntities.isEmpty()){
			return null;
		}
		latestPlanId = planRepository.findLatestToHub(hubs).get(0).getId();
		return latestPlanId;
	}
	@Override
	public List<PlanOrder> getOrdersByActiveVehicleIds(List<Long> activeVehicleIds, Principal principal) {

		List<PlanOrder> planOrders = new ArrayList<>();

		if (principalService.isSuperAdmin(principal)) {
			planOrders = mapper.mapToPlanOrders(
					planOrdersRepository.findByActiveVehicleIdsGroupByActiveVehicles(activeVehicleIds));
		}

		else {
			List<Long> hubs = principalService.getHubs(principal);
			planOrders = mapper.mapToPlanOrders(planOrdersRepository
					.findByActiveVehicleIdsGroupByActiveVehiclesRelatedToHub(activeVehicleIds, hubs));
		}

		return planOrders;
	}

	private String getDriverName(Long id) {
		UsersEntity user = usersRepository.findOne(id);
		String userName = user.getFirstname() + " " + user.getLastname();
		return userName;
	}

	@Override
	public List<ActiveVehicleDto> findAllActiveVehiclesInfo(Principal principal,Long hubId) {
		List<ActiveVehicleDto> activeVehicleDtos = new ArrayList<>();
		List<ActiveVehicleEntity> data = new ArrayList<>();
		List<Long> hubs = new ArrayList<>();
		if (principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)) {
			if(hubId !=null){
				hubs.add(hubId);
			}
		}
		else {
			hubs = principalService.getHubs(principal);
		}
		Long planId = getLatestPlanIdAndHubs(principal,hubs);
		data = planOrdersRepository.getAllActiveVehicleInfoRelatedToHub(planId, hubs);

		for (ActiveVehicleEntity activeVehicle : data) {
			ActiveVehicleDto activeVehicleDto = new ActiveVehicleDto();
			activeVehicleDto.setActiveVehicleId(activeVehicle.getId());
			activeVehicleDto.setWorkShiftFrom(activeVehicle.getWorkShift().getDateFrom());
			activeVehicleDto.setWorkShiftTo(activeVehicle.getWorkShift().getDateTo());
			activeVehicleDto.setVehicleType(activeVehicle.getVehicle().getVehicleType().getType());
			activeVehicleDto.setDriverName(getDriverName(activeVehicle.getDriver().getId()));
			activeVehicleDtos.add(activeVehicleDto);
		}

		return activeVehicleDtos;

	}

}
