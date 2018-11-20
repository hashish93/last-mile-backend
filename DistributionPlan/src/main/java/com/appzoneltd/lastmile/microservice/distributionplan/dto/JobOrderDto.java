package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.math.BigDecimal;
import java.util.Date;

public class JobOrderDto {

	private String timeFrom;
	private String timeTo;
	private BigDecimal actualWeight;
	private Long departureTime;
	private Long arrivalTime;
	private Long priority;
	private String timeTakenRoutingEngineInText;
	private String jobAddress;
	private String jobType;
	private String status ;
	private String packageType;

	public JobOrderDto() {

	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public BigDecimal getActualWeight() {
		return actualWeight;
	}

	public Long getPriority() {
		return priority;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public void setActualWeight(BigDecimal actualWeight) {
		this.actualWeight = actualWeight;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getTimeTakenRoutingEngineInText() {
		return timeTakenRoutingEngineInText;
	}

	public void setTimeTakenRoutingEngineInText(String timeTakenRoutingEngineInText) {
		this.timeTakenRoutingEngineInText = timeTakenRoutingEngineInText;
	}

	public Long getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Long departureTime) {
		this.departureTime = departureTime;
	}

	public Long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
}
