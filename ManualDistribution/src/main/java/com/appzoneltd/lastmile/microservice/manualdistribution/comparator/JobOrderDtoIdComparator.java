package com.appzoneltd.lastmile.microservice.manualdistribution.comparator;

import java.util.Comparator;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.JobOrderDto;

public class JobOrderDtoIdComparator implements Comparator<JobOrderDto> {

	@Override
	public int compare(JobOrderDto o1, JobOrderDto o2) {
		return o1.getId().compareTo(o2.getId());
	}

}
