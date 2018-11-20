package com.appzoneltd.lastmile.microservices.stats.controller;

import com.appzoneltd.lastmile.microservices.stats.dto.BuildingStatistics;
import com.appzoneltd.lastmile.microservices.stats.service.BuildingStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alaa.nabil on 10/17/2017.
 */
@RestController
public class BuildingStatisticsController {

    private final BuildingStatisticsService buildingStatisticsService;

    @Autowired
    public BuildingStatisticsController(BuildingStatisticsService buildingStatisticsService) {
        this.buildingStatisticsService = buildingStatisticsService;
    }

    @PostMapping(value = "/buildingsStatistics", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BuildingStatistics>> buildingsStatistics(@RequestBody List<Long> hubIds) {
        final List<BuildingStatistics> buildingsStatistics = buildingStatisticsService.calculateBuildingsStatistics(hubIds);
        return ResponseEntity.ok(buildingsStatistics);
    }
}
