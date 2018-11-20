package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.stats.reports.repo.RequestTypeSummeryRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@Service
public class PickupRequestStatisticsImp implements PickupRequestStatistics {

	@Autowired
	private RequestTypeSummeryRepository requestTypeSummeryRepository;

	@Autowired
	private PrincipalService principalService;

	@Override
	public List<StatsResponse> getAllPickupRequestStatistics(Principal principal,
			ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();

		if (chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getHubIds() != null) {
			List<Long> hubIds = principalService.getHubs(principal);
			result = getAllPickupRequest(chartDurationRequest.getPeriod(), hubIds);

		}

		if (!chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getHubIds() != null) {
			List<Long> hubIds = chartDurationRequest.getHubIds().stream().collect(Collectors.toList());
			result = getAllPickupRequest(chartDurationRequest.getPeriod(), hubIds);
		}

		return result;
	}

	private List<StatsResponse> getAllPickupRequest(String period, List<Long> hubIds) {
		List<StatsResponse> result = new ArrayList<>();
		result.add(getTotalOnDemandPickups(period, hubIds));
		result.add(getTotalScheduledPickups(period, hubIds));
		return result;
	}

	private StatsResponse getTotalOnDemandPickups(String period, List<Long> hubIds) {
		Long totalOnDemandPickups = requestTypeSummeryRepository.fetchTotalOnDemandPickups(hubIds, period);

		if (totalOnDemandPickups != null)
			return new StatsResponse("ON-DEMAND", totalOnDemandPickups);

		return new StatsResponse("ON-DEMAND", 0L);

	}

	private StatsResponse getTotalScheduledPickups(String period, List<Long> hubIds) {
		Long totalScheduledPickups = requestTypeSummeryRepository.fetchTotalScheduledPickups(hubIds, period);

		if (totalScheduledPickups != null)
			return new StatsResponse("SCHEDULED", totalScheduledPickups);

		return new StatsResponse("SCHEDULED", 0L);

	}

}
