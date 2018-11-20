package com.appzoneltd.lastmile.microservices.stats.service;

import java.security.Principal;
import java.util.List;

import com.appzoneltd.lastmile.microservices.stats.service.pojo.ChartDurationRequest;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

public interface PickupRequestStatistics {
	
	List<StatsResponse> getAllPickupRequestStatistics(Principal principal , ChartDurationRequest chartDurationRequest);

}
