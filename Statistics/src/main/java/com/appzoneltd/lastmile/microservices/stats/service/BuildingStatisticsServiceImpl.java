package com.appzoneltd.lastmile.microservices.stats.service;

import com.appzoneltd.lastmile.microservices.stats.dto.BuildingRank;
import com.appzoneltd.lastmile.microservices.stats.dto.BuildingServingArea;
import com.appzoneltd.lastmile.microservices.stats.dto.BuildingStatistics;
import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservices.stats.lastmile.repo.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 10/17/2017.
 */
@Service
@Transactional(readOnly = true)
public class BuildingStatisticsServiceImpl implements BuildingStatisticsService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingStatisticsServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<BuildingStatistics> calculateBuildingsStatistics(List<Long> hubIds) {
        final List<BuildingStatistics> buildingsStatistics = buildingRepository.findAllByBuildingIdIn(hubIds).parallelStream()
                .map(this::mapToBuildingStatistics)
                .sorted(Comparator.comparingInt(BuildingStatistics::getRequests).reversed())
                .collect(Collectors.toList());

        final double[] intervals = calculateIntervals(getBuildingStatisticsMinThreshold(buildingsStatistics), getBuildingStatisticsMaxThreshold(buildingsStatistics));

        buildingsStatistics.forEach(buildingStatistics -> buildingStatistics.setBuildingRank(buildingStatistics.getRequests() < intervals[1] ? BuildingRank.LOW :
                (intervals[1] <= buildingStatistics.getRequests() && buildingStatistics.getRequests() < intervals[2]) ? BuildingRank.MODERATE :
                        (intervals[2] <= buildingStatistics.getRequests() && buildingStatistics.getRequests() < intervals[3]) ? BuildingRank.BUSY :
                                BuildingRank.EXTREMELY_BUSY));

        assignBuildingOrder(buildingsStatistics);

        return buildingsStatistics;
    }

    private double[] calculateIntervals(final int min, final int max) {
        final double[] intervals = new double[5];
        final double categoryInterval = (max - min) / 4;
        intervals[0] = min;
        intervals[4] = max;
        for (int index = 1; index < 4; index++) {
            intervals[index] = intervals[index - 1] + categoryInterval;
        }
        return intervals;
    }

    private int getBuildingStatisticsMinThreshold(List<BuildingStatistics> buildingsStatistics) {
        final Optional<BuildingStatistics> min = buildingsStatistics.parallelStream().min(Comparator.comparingInt(BuildingStatistics::getRequests));
        return min.map(BuildingStatistics::getRequests).orElse(0);
    }

    private int getBuildingStatisticsMaxThreshold(List<BuildingStatistics> buildingsStatistics) {
        final Optional<BuildingStatistics> max = buildingsStatistics.parallelStream().max(Comparator.comparingInt(BuildingStatistics::getRequests));
        return max.map(BuildingStatistics::getRequests).orElse(0);
    }

    private void assignBuildingOrder(List<BuildingStatistics> buildingsStatistics) {
        for (int i = 0; i < buildingsStatistics.size(); i++) {
            buildingsStatistics.get(i).setRankNumber(String.valueOf(i + 1));
        }
    }

    private BuildingStatistics mapToBuildingStatistics(BuildingEntity buildingEntity) {
        return new BuildingStatistics(buildingEntity.getBuildingId(), buildingEntity.getBuildingname(), buildingEntity.getBuildingnumber() + ", " + buildingEntity.getStreet() + ", " + buildingEntity.getArea()
                , buildingEntity.getLatitude(), buildingEntity.getLongitude()
                , buildingEntity.getPickupRequestEntities().size()
                , buildingEntity.getBuildingServingAreas().stream().map(buildingServingArea -> new BuildingServingArea(buildingServingArea.getLatitude(), buildingServingArea.getLongitude())).collect(Collectors.toList()), buildingEntity.getCountry().getNameEn(), buildingEntity.getCity().getNameEn());
    }
}
