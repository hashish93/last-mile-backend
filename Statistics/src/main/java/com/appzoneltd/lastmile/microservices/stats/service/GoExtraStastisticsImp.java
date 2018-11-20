package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.stats.reports.repo.GoExtraSummeryRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@Service
public class GoExtraStastisticsImp implements GoExtraStastistics {

	@Autowired
	private GoExtraSummeryRepository goExtraSummeryRepository;

	@Autowired
	private PrincipalService principalService;

	@Override
	public List<StatsResponse> goExtraStastisticsInfo(Principal principal, ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();

		if (chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getHubIds() != null) {
			List<Long> hubIds = principalService.getHubs(principal);
			result = getGoExtraStastistics(chartDurationRequest.getPeriod(), hubIds);

		}

		if (!chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getHubIds() != null) {
			List<Long> hubIds = chartDurationRequest.getHubIds();
			result = getGoExtraStastistics(chartDurationRequest.getPeriod(), hubIds);
		}

		return result;
	}

	private List<StatsResponse> getGoExtraStastistics(String period, List<Long> hubIds) {
		List<StatsResponse> result = new ArrayList<>();
		result.add(countRejectOndemand(period, hubIds));
		result.add(countCancelledOndemand(period, hubIds));
		result.add(countAcknowledgedOndemand(period, hubIds));
		result.add(countALLOndemand(period, hubIds));

		return result;

	}

	private StatsResponse countRejectOndemand(String period, List<Long> hubIds) {

		Long countRejectOndemand = goExtraSummeryRepository.countRejectOndemand(hubIds, period);
		if (countRejectOndemand != null)
			return new StatsResponse("REJECTED", countRejectOndemand);

		return new StatsResponse("REJECTED", 0L);
	}

	private StatsResponse countCancelledOndemand(String period, List<Long> hubIds) {

		Long countCancelledOndemand = goExtraSummeryRepository.countCancelledOndemand(hubIds, period);
		if (countCancelledOndemand != null)
			return new StatsResponse("CANCELLED", countCancelledOndemand);

		return new StatsResponse("CANCELLED", 0L);
	}

	private StatsResponse countAcknowledgedOndemand(String period, List<Long> hubIds) {

		Long countAcknowledgedOndemand = goExtraSummeryRepository.countAcknowledgedOndemand(hubIds, period);
		if (countAcknowledgedOndemand != null)
			return new StatsResponse("ACKNOWLEDGED", countAcknowledgedOndemand);

		return new StatsResponse("ACKNOWLEDGED", 0L);
	}

	private StatsResponse countALLOndemand(String period, List<Long> hubIds) {

		Long countAllOndemand = 0L;
		Long countAcknowledgedOndemand = goExtraSummeryRepository.countAcknowledgedOndemand(hubIds, period);
		Long countCancelledOndemand = goExtraSummeryRepository.countCancelledOndemand(hubIds, period);
		Long countRejectOndemand = goExtraSummeryRepository.countRejectOndemand(hubIds, period);

		if (countAcknowledgedOndemand != null && countCancelledOndemand != null && countRejectOndemand != null) {
			countAllOndemand = countAcknowledgedOndemand + countCancelledOndemand + countRejectOndemand;

		}

		return new StatsResponse("ALL", countAllOndemand);
	}

}
