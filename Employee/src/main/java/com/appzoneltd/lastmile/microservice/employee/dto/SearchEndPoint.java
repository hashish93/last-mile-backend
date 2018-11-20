package com.appzoneltd.lastmile.microservice.employee.dto;

import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;

import lombok.Data;

@Data
public class SearchEndPoint extends EndPointParameter {
	private String key;
}
