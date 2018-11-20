package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.stats.reports.repo.RequestTypeSummeryRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@Service
public class RequestTypeStatisticsImp implements RequestTypeStatistics {

	@Autowired
	private RequestTypeSummeryRepository requestTypeSummeryRepository;

	@Autowired
	private PrincipalService principalService;

	@Override
	public List<StatsResponse> getAllRequestTypeStatistics(Principal principal,
			ChartDurationRequest chartDurationRequest) {

		List<StatsResponse> result = new ArrayList<>();

		if (chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getHubIds() != null) {
			List<Long> hubIds = principalService.getHubs(principal);
			result = getAllRequestType(chartDurationRequest.getPeriod(), hubIds);

		}

		if (!chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getHubIds() != null) {
			List<Long> hubIds = chartDurationRequest.getHubIds();
			result = getAllRequestType(chartDurationRequest.getPeriod(), hubIds);
		}

		return result;
	}

	private List<StatsResponse> getAllRequestType(String period, List<Long> hubIds) {
		List<StatsResponse> result = new ArrayList<>();
		result.add(getTotalOnDemandPickups(period, hubIds));
		result.add(getTotalScheduledPickups(period, hubIds));
		result.add(getTotalDeliveries(period, hubIds));
		result.add(getTotalReturns(period, hubIds));
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

	private StatsResponse getTotalDeliveries(String period, List<Long> hubIds) {
		Long totalDeliveries = requestTypeSummeryRepository.fetchTotalDeliveries(hubIds, period);

		if (totalDeliveries != null)
			return new StatsResponse("DELIVERY", totalDeliveries);

		return new StatsResponse("DELIVERY", 0L);

	}

	private StatsResponse getTotalReturns(String period, List<Long> hubIds) {
		Long totalReturns = requestTypeSummeryRepository.fetchTotalScheduledPickups(hubIds, period);

		if (totalReturns != null)
			return new StatsResponse("RETURN", totalReturns);

		return new StatsResponse("RETURN", 0L);

	}

}
