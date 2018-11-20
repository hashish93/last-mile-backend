package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.stats.reports.repo.PickupStatisticsSummeryRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@Service
public class PickupRequestStatusStatisticsImp implements PickupRequestStatusStatistics {

	@Autowired
	private PickupStatisticsSummeryRepository pickupStatisticsSummeryRepository;

	@Autowired
	private PrincipalService principalService;

	@Override
	public List<StatsResponse> pickupRequestStatusStatisticsInfo(Principal principal,
			ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();

		if (principalService.isSuperAdmin(principal)) {
			if (chartDurationRequest.getHubIds().isEmpty()) {
				List<Long> hubIds = principalService.getHubs(principal);
				chartDurationRequest.setHubIds(hubIds);
				result = getPickupRequestStatusStatisticsInfo(chartDurationRequest);
				result.add(countNoCoveragePickupOrder(chartDurationRequest.getPeriod(),
						chartDurationRequest.getRequestType()));
			} else {
				result = getPickupRequestStatusStatisticsInfo(chartDurationRequest);
				result.add(countNoCoveragePickupOrder(chartDurationRequest.getPeriod(),
						chartDurationRequest.getRequestType()));
			}

		}

		if (!principalService.isSuperAdmin(principal)) {
			if (chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getHubIds() != null) {
				List<Long> hubIds = principalService.getHubs(principal);
				chartDurationRequest.setHubIds(hubIds);
				result = getPickupRequestStatusStatisticsInfo(chartDurationRequest);
			} else {
				result = getPickupRequestStatusStatisticsInfo(chartDurationRequest);
			}

		}

		return result;
	}

	private List<StatsResponse> getPickupRequestStatusStatisticsInfo(ChartDurationRequest chartDurationRequest) {

		List<StatsResponse> result = new ArrayList<>();

		result.add(countSucceededPickupOrder(chartDurationRequest.getPeriod(), chartDurationRequest.getRequestType(),
				chartDurationRequest.getHubIds()));
		result.add(countCancelledPickupOrder(chartDurationRequest.getPeriod(), chartDurationRequest.getRequestType(),
				chartDurationRequest.getHubIds()));
		result.add(countNoCapacityPickupOrder(chartDurationRequest.getPeriod(), chartDurationRequest.getRequestType(),
				chartDurationRequest.getHubIds()));

		return result;

	}

	private StatsResponse countSucceededPickupOrder(String period, String requestType, List<Long> hubIds) {
		Long countSucceededPickupOrder = pickupStatisticsSummeryRepository.countSucceededPickupOrder(period,
				requestType, hubIds);

		if (countSucceededPickupOrder != null)
			return new StatsResponse("SUCCEEDED", countSucceededPickupOrder);

		return new StatsResponse("SUCCEEDED", 0L);

	}

	private StatsResponse countCancelledPickupOrder(String period, String requestType, List<Long> hubIds) {
		Long countSucceededPickupOrder = pickupStatisticsSummeryRepository.countCancelledPickupOrder(period,
				requestType, hubIds);

		if (countSucceededPickupOrder != null)
			return new StatsResponse("CANCELLED", countSucceededPickupOrder);

		return new StatsResponse("CANCELLED", 0L);

	}

	private StatsResponse countNoCapacityPickupOrder(String period, String requestType, List<Long> hubIds) {
		Long countSucceededPickupOrder = pickupStatisticsSummeryRepository.countNoCapacityPickupOrder(period,
				requestType, hubIds);

		if (countSucceededPickupOrder != null)
			return new StatsResponse("NOCAPACITY", countSucceededPickupOrder);

		return new StatsResponse("NOCAPACITY", 0L);

	}

	private StatsResponse countNoCoveragePickupOrder(String period, String requestType) {
		Long countSucceededPickupOrder = pickupStatisticsSummeryRepository.countNoCoveragePickupOrder(period,
				requestType);

		if (countSucceededPickupOrder != null)
			return new StatsResponse("NOCOVERAGE", countSucceededPickupOrder);

		return new StatsResponse("NOCOVERAGE", 0L);

	}

}
