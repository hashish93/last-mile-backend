package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import java.io.Serializable;
import java.util.Date;

public class JobOrder implements Serializable {

	protected static final long serialVersionUID = 1L;

	protected Long jobOrderId;
	protected String timeFrom;
	protected Long hubId;
	protected String timeTo;
	protected String jobLongitude;
	protected String jobLatitude;
	protected String jobAddress;
	protected Date jobDate;
	protected String jobStatus;

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public void setJobOrderId(Long jobOrderId) {
		this.jobOrderId = jobOrderId;
	}

	public void setJobLongitude(String jobLongitude) {
		this.jobLongitude = jobLongitude;
	}

	public void setJobLatitude(String jobLatitude) {
		this.jobLatitude = jobLatitude;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Long getJobOrderId() {
		return jobOrderId;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public String getJobLongitude() {
		return jobLongitude;
	}

	public String getJobLatitude() {
		return jobLatitude;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public String getJobStatus() {
		return jobStatus;
	}

}
