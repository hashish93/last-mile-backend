package com.appzoneltd.lastmile.microservice.customer.model;

import lombok.Data;

@Data
public class CustomerStatistics {

	String labels;
	Long data;

	public CustomerStatistics(String labels , Long data) {
		this.labels = labels;
		this.data = data;
	}

}
