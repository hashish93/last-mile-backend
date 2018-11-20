package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
@Data
public class RescheduledOndemandDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long requestId;
    private Long packageId;
    private Long requesterId;
    private String timeFrom;
    private String timeTo;
    private Date pickupDate;
    private boolean admin;
}
