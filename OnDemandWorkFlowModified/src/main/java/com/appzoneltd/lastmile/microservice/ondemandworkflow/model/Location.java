package com.appzoneltd.lastmile.microservice.ondemandworkflow.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Location implements Serializable {
	private static final long serialVersionUID = -6043719507258236587L;
	private String latitude;
	private String longitude;

}
