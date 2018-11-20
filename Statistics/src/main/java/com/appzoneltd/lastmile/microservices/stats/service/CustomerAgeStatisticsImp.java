package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.CustomerAgeSummeryEntity;
import com.appzoneltd.lastmile.microservices.stats.reports.repo.CustomerAgeSummeryRepository;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@Service
public class CustomerAgeStatisticsImp implements CustomerAgeStatistics {

	@Autowired
	private CustomerAgeSummeryRepository customerAgeSummeryRepository;

	@Autowired
	private PrincipalService principalService;

	@Override
	public List<StatsResponse> getAllCustomerAges(Principal principal) {

		List<StatsResponse> result = new ArrayList<>();

		if (principalService.isSuperAdmin(principal)) {

			result = getAllAgeForCustomers();

		}
		return result;
	}

	private List<StatsResponse> getAllAgeForCustomers() {
		List<StatsResponse> result = new ArrayList<>();

		List<CustomerAgeSummeryEntity> outLista = customerAgeSummeryRepository.findAll();

		if (outLista == null || outLista.isEmpty()) {
			createEmptyResponseList(result);
		}
		//else
		CustomerAgeSummeryEntity dbEntity = outLista.get(0);

		result.add(new StatsResponse("LESSTHAN21",dbEntity.getAgeLessThan21()));

		result.add(new StatsResponse("AGEBETWEEN21AND30",dbEntity.getAgeBetween21And30()));

		result.add(new StatsResponse("AGEBETWEEN31AND45",dbEntity.getAgeBetween31And45()));

		result.add(new StatsResponse("ABOVE45",dbEntity.getAgeAbove45()));

		result.add(new StatsResponse("OTHERAGES",dbEntity.getOthers()));

		return result;
	}

	private void createEmptyResponseList(List<StatsResponse> result) {
		result.add(new StatsResponse("LESSTHAN21"));

		result.add(new StatsResponse("AGEBETWEEN21AND30"));

		result.add(new StatsResponse("AGEBETWEEN31AND45"));

		result.add(new StatsResponse("ABOVE45"));

		result.add(new StatsResponse("OTHERAGES"));

	}


}
