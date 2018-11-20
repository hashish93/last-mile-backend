package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
@Data
public class CustomerAlternativesDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long requestId;
    private Long requesterId;
    private Long packageId;
    private boolean admin;    
}
