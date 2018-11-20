package com.appzoneltd.lastmile.microservice.workflowservice.model;

import java.util.List;
import lombok.Data;

@Data
public class VehicleCapacity {
	private Long vehicleId;
	private Integer capacity;
	private List<Double> orderWeights;
}
