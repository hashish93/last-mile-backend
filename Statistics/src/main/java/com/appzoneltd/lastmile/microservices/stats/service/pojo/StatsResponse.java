package com.appzoneltd.lastmile.microservices.stats.service.pojo;

import java.io.Serializable;

public class StatsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2820334688031882179L;

	private String labels;
	private Object data;

	public StatsResponse(String labels, Object data) {

		this.labels = labels;
		this.data = data;
	}
	
	public StatsResponse(String labels) {

		this.labels = labels;
		this.data = new Long(0L);
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Object getData() {
		return data;
	}

	public void Object(String data) {
		this.data = data;
	}

}
