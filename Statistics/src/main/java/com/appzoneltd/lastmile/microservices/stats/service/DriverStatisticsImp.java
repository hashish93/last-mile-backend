package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.stats.reports.repo.DriverRatingSummeryRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@Service
public class DriverStatisticsImp implements DriverStatistics {

	@Autowired
	private PrincipalService principalService;

	@Autowired
	private DriverRatingSummeryRepository driverRatingSummeryRepository;

	@Override
	public List<StatsResponse> driversRating(Principal principal, ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();

		if (principalService.isSuperAdmin(principal)) {
			if (chartDurationRequest.getHubIds().isEmpty()) {
				List<Long> hubIds = principalService.getHubs(principal);
				chartDurationRequest.setHubIds(hubIds);
				result = getDriversRating(chartDurationRequest);

			} else {
				result = getCorporateDriversRating(chartDurationRequest);
			}

		}

		if (principalService.isFreelancerAdmin(principal)) {
			result = getFreelancerDriversRating();

		}

		else if (!principalService.isSuperAdmin(principal) && !principalService.isFreelancerAdmin(principal)) {

			if (chartDurationRequest.getHubIds().isEmpty()) {
				List<Long> hubIds = principalService.getHubs(principal);
				chartDurationRequest.setHubIds(hubIds);
				result = getCorporateDriversRating(chartDurationRequest);
			} else {

				result = getCorporateDriversRating(chartDurationRequest);
			}

		}

		return result;
	}

	@Override
	public List<StatsResponse> getDriversRating(ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();

		if ("CORPORATE".equalsIgnoreCase(chartDurationRequest.getDriverType()))
			result = getCorporateDriversRating(chartDurationRequest);

		if ("FREELANCERS".equalsIgnoreCase(chartDurationRequest.getDriverType()))
			result = getFreelancerDriversRating();

		if ("ALL".equalsIgnoreCase(chartDurationRequest.getDriverType()))
			result = getAllDriversRating(chartDurationRequest);

		return result;
	}

	private List<StatsResponse> getFreelancerDriversRating() {
		List<StatsResponse> result = new ArrayList<>();

		String type = "FREELANCER_DRIVER";
		List<Long> hubIds = Arrays.asList(0L);
		Long freelancerDriversOneStars = driverRatingSummeryRepository.countDriverRatingOneStar(type, hubIds);
		Long freelancerDriversTwoStars = driverRatingSummeryRepository.countDriverRatingTwoStars(type, hubIds);
		Long freelancerDriversThreeStars = driverRatingSummeryRepository.countDriverRatingThreeStars(type, hubIds);
		Long freelancerDriversFourStars = driverRatingSummeryRepository.countDriverRatingFourStars(type, hubIds);
		Long freelancerDriversFiveStasr = driverRatingSummeryRepository.countDriverRatingFiveStars(type, hubIds);

		result.add(new StatsResponse("ONESTARS", freelancerDriversOneStars));
		result.add(new StatsResponse("TWOSTARS", freelancerDriversTwoStars));
		result.add(new StatsResponse("THREESTARS", freelancerDriversThreeStars));
		result.add(new StatsResponse("FOURSTARS", freelancerDriversFourStars));
		result.add(new StatsResponse("FIVESTARS", freelancerDriversFiveStasr));

		return result;

	}

	private List<StatsResponse> getCorporateDriversRating(ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();

		String type = "CORPORATE_DRIVER";
		List<Long> hubIds = chartDurationRequest.getHubIds();
		Long CorporateDriversOneStars = driverRatingSummeryRepository.countDriverRatingOneStar(type, hubIds);
		Long CorporateDriversTwoStars = driverRatingSummeryRepository.countDriverRatingTwoStars(type, hubIds);
		Long CorporateDriversThreeStars = driverRatingSummeryRepository.countDriverRatingThreeStars(type, hubIds);
		Long CorporateDriversFourStars = driverRatingSummeryRepository.countDriverRatingFourStars(type, hubIds);
		Long CorporateDriversFiveStasr = driverRatingSummeryRepository.countDriverRatingFiveStars(type, hubIds);

		result.add(new StatsResponse("ONESTARS", CorporateDriversOneStars));
		result.add(new StatsResponse("TWOSTARS", CorporateDriversTwoStars));
		result.add(new StatsResponse("THREESTARS", CorporateDriversThreeStars));
		result.add(new StatsResponse("FOURSTARS", CorporateDriversFourStars));
		result.add(new StatsResponse("FIVESTARS", CorporateDriversFiveStasr));

		return result;

	}

	private List<StatsResponse> getAllDriversRating(ChartDurationRequest chartDurationRequest) {
		List<StatsResponse> result = new ArrayList<>();
		String type = null;
		List<Long> hubIds = new ArrayList<>();

		hubIds = chartDurationRequest.getHubIds();
		type = "CORPORATE_DRIVER";
		Long corporateDriversOneStars = driverRatingSummeryRepository.countDriverRatingOneStar(type, hubIds);
		Long corporateDriversTwoStars = driverRatingSummeryRepository.countDriverRatingTwoStars(type, hubIds);
		Long corporateDriversThreeStars = driverRatingSummeryRepository.countDriverRatingThreeStars(type, hubIds);
		Long corporateDriversFourStars = driverRatingSummeryRepository.countDriverRatingFourStars(type, hubIds);
		Long corporateDriversFiveStasr = driverRatingSummeryRepository.countDriverRatingFiveStars(type, hubIds);

		type = "FREELANCER_DRIVER";
		hubIds = Arrays.asList(0L);
		Long freelancerDriversOneStars = driverRatingSummeryRepository.countDriverRatingOneStar(type, hubIds);
		Long freelancerDriversTwoStars = driverRatingSummeryRepository.countDriverRatingTwoStars(type, hubIds);
		Long freelancerDriversThreeStars = driverRatingSummeryRepository.countDriverRatingThreeStars(type, hubIds);
		Long freelancerDriversFourStars = driverRatingSummeryRepository.countDriverRatingFourStars(type, hubIds);
		Long freelancerDriversFiveStasr = driverRatingSummeryRepository.countDriverRatingFiveStars(type, hubIds);

		Long DriversOneStars = corporateDriversOneStars + freelancerDriversOneStars;
		Long DriversTwoStars = corporateDriversTwoStars + freelancerDriversTwoStars;
		Long DriversThreeStars = corporateDriversThreeStars + freelancerDriversThreeStars;
		Long DriversFourStars = corporateDriversFourStars + freelancerDriversFourStars;
		Long DriversFiveStars = corporateDriversFiveStasr + freelancerDriversFiveStasr;

		result.add(new StatsResponse("ONESTARS", DriversOneStars));
		result.add(new StatsResponse("TWOSTARS", DriversTwoStars));
		result.add(new StatsResponse("THREESTARS", DriversThreeStars));
		result.add(new StatsResponse("FOURSTARS", DriversFourStars));
		result.add(new StatsResponse("FIVESTARS", DriversFiveStars));

		return result;

	}

}
