package com.appzoneltd.lastmile.microservice.manualdistribution.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class JobOrderDto {

	private Long id;
	private String timeFrom;
	private String timeTo;
	private String jobLongitude;
	private String jobLatitude;
	private BigDecimal actualWeight;
	private Long orderPackageId;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private LocalDateTime startTimeFromOrigin;
	private Integer priority;
	private String timeTakenRoutingEngineInText;
	private Long timeTakenValueRoutingEngineInSeconds;
	private String jobAddress;
	private String errorMessage;
	private JobStatus jobStatus;
	private String jobType;

	public JobOrderDto() {

	}

	public JobOrderDto(Long id) {
		this.id = id;
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

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public Integer getPriority() {
		return priority;
	}

	public String getErrorMessage() {
		return errorMessage;
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

	public Long getOrderPackageId() {
		return orderPackageId;
	}

	public void setOrderPackageId(Long orderPackageId) {
		this.orderPackageId = orderPackageId;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public enum JobStatus {
		SUCCESS, FAIL, IN_GRACE_PERIOD
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobOrderDto other = (JobOrderDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getJobLongitude() {
		return jobLongitude;
	}

	public void setJobLongitude(String jobLongitude) {
		this.jobLongitude = jobLongitude;
	}

	public String getJobLatitude() {
		return jobLatitude;
	}

	public void setJobLatitude(String jobLatitude) {
		this.jobLatitude = jobLatitude;
	}

	public String getTimeTakenRoutingEngineInText() {
		return timeTakenRoutingEngineInText;
	}

	public void setTimeTakenRoutingEngineInText(String timeTakenRoutingEngineInText) {
		this.timeTakenRoutingEngineInText = timeTakenRoutingEngineInText;
	}

	public Long getTimeTakenValueRoutingEngineInSeconds() {
		return timeTakenValueRoutingEngineInSeconds;
	}

	public void setTimeTakenValueRoutingEngineInSeconds(Long timeTakenValueRoutingEngineInSeconds) {
		this.timeTakenValueRoutingEngineInSeconds = timeTakenValueRoutingEngineInSeconds;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public LocalDateTime getStartTimeFromOrigin() {
		return startTimeFromOrigin;
	}

	public void setStartTimeFromOrigin(LocalDateTime startTimeFromOrigin) {
		this.startTimeFromOrigin = startTimeFromOrigin;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

}
