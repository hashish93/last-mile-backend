package com.appzoneltd.lastmile.microservices.stats.service.pojo;

import java.io.Serializable;
import java.util.List;

public class ChartDurationRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2447345828106002960L;

	private List<Long> hubIds;
	private String period;
	private List<Long> ids;
	private String requestType;
	private String driverType;

	public ChartDurationRequest() {
	}

	public ChartDurationRequest(List<Long> hubIds) {
		this.hubIds = hubIds;
	}

	public List<Long> getHubIds() {
		return hubIds;
	}

	public void setHubIds(List<Long> hubIds) {
		this.hubIds = hubIds;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

}
