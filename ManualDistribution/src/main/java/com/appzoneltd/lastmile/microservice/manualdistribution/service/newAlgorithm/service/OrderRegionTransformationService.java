package com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.JobOrder;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.model.LocationPoint;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.model.OrderPoint;
import com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.model.Region;

@Service
public class OrderRegionTransformationService {

	@Autowired
	private NearByDetectionService nearByDetectionService;

	private List<OrderPoint> transformOrdersToPoints(List<JobOrder> jobOrders) {
		List<OrderPoint> orderPoints = new ArrayList<>();
		for (JobOrder jobOrder : jobOrders) {
			OrderPoint orderPoint = new OrderPoint();
			orderPoint.setOrderId(jobOrder.getJobOrderId());
			orderPoint.setAddress(jobOrder.getJobAddress());
			orderPoint.setDate(jobOrder.getJobDate());
			// GET LOCATION
			LocationPoint locationPoint = new LocationPoint();
			locationPoint.setLatitude(jobOrder.getJobLatitude());
			locationPoint.setLongitude(jobOrder.getJobLongitude());

			orderPoint.setLocationPoint(locationPoint);
			orderPoint.setStatus(jobOrder.getJobStatus());
			orderPoint.setTimeFrom(jobOrder.getTimeFrom());
			orderPoint.setTimeTo(jobOrder.getTimeTo());
			/// ADD TO LIST
			orderPoints.add(orderPoint);
		}
		return orderPoints;
	}

	private Region regionGenerator(OrderPoint orderPoint, int regionNumber) {

		Region region = new Region();
		List<OrderPoint> points = new ArrayList<>();
		region.setName("Region" + regionNumber);
		points.add(orderPoint);
		region.setOrderPoints(points);

		return region;
	}

	private List<Region> collectPointsToRegions(List<OrderPoint> orderPoints) {
		int regionNumber = 1;
		OrderPoint orderPoint = orderPoints.get(0);
		List<Region> regions = new ArrayList<>();

		Region initRegion = regionGenerator(orderPoint, regionNumber);
		regions.add(initRegion);

		for (int index = 1; index < orderPoints.size(); index++) {
			int regionIndex = getRegionWhichPointBelongsTo(regions, orderPoints.get(index));
			if (regionIndex == -1) {
				Region region = regionGenerator(orderPoints.get(index), ++regionNumber);
				regions.add(region);
			} else {
				regions.get(regionIndex).getOrderPoints().add(orderPoints.get(index));
			}
		}
		return regions;
	}

	private int getRegionWhichPointBelongsTo(List<Region> regions, OrderPoint orderPoint) {
		int regionIndex = -1;
		for (int index = 0; index < regions.size(); index++) {
			if (isPointBelongToRegion(regions.get(index), orderPoint)) {
				regionIndex = index;
				break;
			}
		}
		return regionIndex;
	}

	private boolean isPointBelongToRegion(Region region, OrderPoint orderPoint) {
		boolean isBelong = false;
		for (OrderPoint basePoint : region.getOrderPoints()) {
			if (isPointBelongToPointDiameter(basePoint, orderPoint)) {
				isBelong = true;
				break;
			}
		}
		return isBelong;
	}

	private boolean isPointBelongToPointDiameter(OrderPoint basePoint, OrderPoint otherPoint) {
		return nearByDetectionService.isLocationInsideDiameter(basePoint.getLocationPoint(),
				otherPoint.getLocationPoint(), new Double(10));
	}

	public List<Region> process(List<JobOrder> jobOrders) {

		List<Region> regions = new ArrayList<>();
		List<OrderPoint> orderPoints = transformOrdersToPoints(jobOrders);
		System.out.println(">> ORDER POINTS "+orderPoints.toString());
			
		if (orderPoints.size()>0) {
			regions = collectPointsToRegions(orderPoints);
		}
		return regions;

	}

}
