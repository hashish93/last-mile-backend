package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ActionNeededBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long packageId;
	private Long hubId;
	private boolean switched;
	
}
