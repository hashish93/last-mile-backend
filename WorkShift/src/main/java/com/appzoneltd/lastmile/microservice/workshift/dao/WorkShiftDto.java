package com.appzoneltd.lastmile.microservice.workshift.dao;

import java.util.Date;

import lombok.Data;


@Data
public class WorkShiftDto {

	private Long id;

	private Date from;

	private Date to;
	
	private Long version ;


}
