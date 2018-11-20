package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import lombok.Data;

@Data
public class CustomerAgeSummeryReportModel {
	private Long Age;
	private Long count;
	public CustomerAgeSummeryReportModel(Long age, Long count) {
		Age = age;
		this.count = count;
	}
	public CustomerAgeSummeryReportModel(){};
}
