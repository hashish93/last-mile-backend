package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.PackageTypeSummaryEntity;
import com.appzoneltd.lastmile.microservices.stats.reports.repo.PackageTypeSummaryRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@Service
public class PackageStatisticsImp implements PackageStatistics {

	@Autowired
	private PackageTypeSummaryRepository packageTypeSummaryRepository;

	@Autowired
	private PrincipalService principalService;

	@Override
	public List<StatsResponse> getAllPackagesWithWeight(Principal principal,
			ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();

		if (chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getIds().isEmpty()) {
			List<Long> hubIds = principalService.getHubs(principal);
			List<Long> ids = packageTypeSummaryRepository.findAllPackageTypeIds();
			chartDurationRequest.setHubIds(hubIds);
			chartDurationRequest.setIds(ids);
			result = getPackagesSummary(chartDurationRequest);
		}

		if (!chartDurationRequest.getHubIds().isEmpty() && chartDurationRequest.getIds().isEmpty()) {
			List<Long> ids = packageTypeSummaryRepository.findAllPackageTypeIds();
			chartDurationRequest.setIds(ids);
			result = getPackagesSummary(chartDurationRequest);

		}

		if (!chartDurationRequest.getHubIds().isEmpty() && !chartDurationRequest.getIds().isEmpty()) {
			result = getPackagesSummary(chartDurationRequest);
		}

		if (chartDurationRequest.getHubIds().isEmpty() && !chartDurationRequest.getIds().isEmpty()) {
			List<Long> hubIds = principalService.getHubs(principal);
			chartDurationRequest.setHubIds(hubIds);
			result = getPackagesSummary(chartDurationRequest);

		}

		return result;

	}

	private List<StatsResponse> getPackagesSummary(ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();
		List<PackageTypeSummaryEntity> entities = packageTypeSummaryRepository.findAll(chartDurationRequest.getPeriod(),
				chartDurationRequest.getHubIds(), chartDurationRequest.getIds());

		Long countPackagesLessThan5Kg = 0L;
		Long countPackagesLessThan10Kg = 0L;
		Long countPackagesLessThan25Kg = 0L;

		for (PackageTypeSummaryEntity entity : entities) {
			countPackagesLessThan5Kg += entity.getPackageLess5Kg();
			countPackagesLessThan10Kg += entity.getPackageLess10Kg();
			countPackagesLessThan25Kg += entity.getPackageLess25Kg();
		}

		result.add(new StatsResponse("PACKGESLESSTHAN5KG", countPackagesLessThan5Kg));
		result.add(new StatsResponse("PACKGESLESSTHAN10KG", countPackagesLessThan10Kg));
		result.add(new StatsResponse("PACKGESLESSTHAN25KG", countPackagesLessThan25Kg));

		return result;

	}

}
