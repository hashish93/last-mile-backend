package com.appzoneltd.lastmile.microservice.distributionplan.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.*;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alaa.nabil on 2/15/2017.
 */
@Service
public class PlanServiceImpl implements PlanService {

	private final PlanRepository planRepository;
	private final PlanTmpRepository planTmpRepository;
	private final Mapper mapper;
	private final PlanOrdersRepository planOrdersRepository;
	private final PlanOrdersTmpRepository planOrdersTmpRepository;
	private final PrincipalService principalService;

	@Autowired
	public PlanServiceImpl(PlanRepository planRepository, PlanTmpRepository planTmpRepository,
			PlanOrdersRepository planOrdersRepository, Mapper mapper, PlanOrdersTmpRepository planOrdersTmpRepository,
			PrincipalService principalService) {
		this.planRepository = planRepository;
		this.planTmpRepository = planTmpRepository;
		this.mapper = mapper;
		this.planOrdersRepository = planOrdersRepository;
		this.planOrdersTmpRepository = planOrdersTmpRepository;
		this.principalService = principalService;
	}

	@Override
	public PlanEntity addPlan(Plan plan) {
		return planRepository.save(mapper.mapToPlanEntity(plan));
	}

	@Override
	public List<Plan> getPlans(Principal principal) {
		List<Plan> plans = new ArrayList<>();
		if (principalService.isSuperAdmin(principal)) {
			plans = ((List<PlanEntity>) planRepository.findAll()).stream()
					.map(planEntity -> mapper.mapToPlanDto(planEntity)).collect(Collectors.toList());
		}
		else {
			List<Long> hubs = principalService.getHubs(principal);
			plans = planRepository.findAllPlanByHub(hubs).stream().map(planEntity -> mapper.mapToPlanDto(planEntity))
					.collect(Collectors.toList());
		}
		return plans;
	}

	@Override
	public List<PlanDetailsDto> planDetails(Principal principal) {
		List<PlanDetailsDto> planDetailsDtos = new ArrayList<>();
		
		if (principalService.isSuperAdmin(principal)){
		if (!planRepository.findLatest().isEmpty()) {
			Long latestPlanId = planRepository.findLatest().get(0).getId();
			List<PlanOrderEntity> planOrderEntities = planOrdersRepository.getPlanDetails(latestPlanId);
			planDetailsDtos = mapper.mapToPlanDetails(planOrderEntities);

		}
		}
		
		else {
			List<Long> hubs=principalService.getHubs(principal);
			if (!planRepository.findLatestToHub(hubs).isEmpty()) {
				Long latestPlanId = planRepository.findLatestToHub(hubs).get(0).getId();
				List<PlanOrderEntity> planOrderEntities = planOrdersRepository.getPlanDetailsToHub(latestPlanId,hubs);
				planDetailsDtos = mapper.mapToPlanDetails(planOrderEntities);

			}
			
		}

		return planDetailsDtos;

	}

	@Override
	public TimedPlanDetailsDto tmpPlanDetails() {
		List<PlanDetailsDto> planDetailsDtos = new ArrayList<>();
		TimedPlanDetailsDto timedPlanDetailsDto = new TimedPlanDetailsDto();
		if (!planTmpRepository.findLatest().isEmpty()) {
			Long latestPlanId = planTmpRepository.findLatest().get(0).getId();
			List<PlanOrderTmpEntity> planOrderEntities = planOrdersTmpRepository.getPlanDetailsTmp(latestPlanId);
			planDetailsDtos = mapper.mapToTmpPlanDetails(planOrderEntities);
			timedPlanDetailsDto.setCreated(planTmpRepository.findLatest().get(0).getCreated());
			timedPlanDetailsDto.setPlanDetails(planDetailsDtos);
		}

		return timedPlanDetailsDto;

	}

}
