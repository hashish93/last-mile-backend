package com.appzoneltd.lastmile.microservices.stats.service;

import java.util.List;

import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

public interface SystemGeneralInfo {
	
	List<StatsResponse> getSystemGeneralInfo();
}
