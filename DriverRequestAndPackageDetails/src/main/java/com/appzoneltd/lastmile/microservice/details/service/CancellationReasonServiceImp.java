package com.appzoneltd.lastmile.microservice.details.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.details.ObjectMapper;
import com.appzoneltd.lastmile.microservice.details.dao.entity.CancelationReasonEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.CancelationReasonJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dto.PickupCancellationReason;

@Service
public class CancellationReasonServiceImp implements CancellationReasonService {

	private final CancelationReasonJpaRepository cancelationReasonJpaRepository;

	@Autowired
	public CancellationReasonServiceImp(CancelationReasonJpaRepository cancelationReasonJpaRepository) {
		super();
		this.cancelationReasonJpaRepository = cancelationReasonJpaRepository;
	}

	@Override
	public List<PickupCancellationReason> getCancellationReasons() {
		return ObjectMapper
				.toPickupCancellationReasons((List<CancelationReasonEntity>) cancelationReasonJpaRepository.findAll());
	}

}
