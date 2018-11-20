package com.appzoneltd.lastmile.microservice.details.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.details.dto.PickupCancellationReason;

public interface CancellationReasonService {
	List<PickupCancellationReason> getCancellationReasons();
}
