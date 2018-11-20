package com.appzoneltd.lastmile.microservice.manualdistribution.dto;

import java.math.BigDecimal;

public class JobOrderTodayDto {
	
	private Long id;
	private String timeFrom;
	private String timeTo;
	private BigDecimal actualWeight;
	private String jobAddress ;
	private String jobPackageType ;
	private String jobType ;
	
	
	public Long getId() {
		return id;
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
	public String getJobAddress() {
		return jobAddress;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}
	public String getJobPackageType() {
		return jobPackageType;
	}
	public void setJobPackageType(String jobPackageType) {
		this.jobPackageType = jobPackageType;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	@Override
	public String toString() {
		return "JobOrderTodayDto{" +
				"id=" + id +
				", timeFrom='" + timeFrom + '\'' +
				", timeTo='" + timeTo + '\'' +
				", actualWeight=" + actualWeight +
				", jobAddress='" + jobAddress + '\'' +
				", jobPackageType='" + jobPackageType + '\'' +
				", jobType='" + jobType + '\'' +
				'}';
	}
}
