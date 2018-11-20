package com.appzoneltd.lastmile.microservices.stats.service;

import com.appzoneltd.lastmile.microservices.stats.dto.BuildingStatistics;

import java.util.List;

/**
 * Created by alaa.nabil on 10/17/2017.
 */
public interface BuildingStatisticsService {
    List<BuildingStatistics> calculateBuildingsStatistics(List<Long> hubIds);
}
