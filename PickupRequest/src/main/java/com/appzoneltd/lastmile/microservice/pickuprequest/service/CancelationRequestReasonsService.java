package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest.CancelationRequest;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest.CancelationRequestDto;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest.CancelationRequestRepositoryImp;

@Service
public class CancelationRequestReasonsService {
	
	private final CancelationRequestRepositoryImp cancelationRequestRepositoryImp ;
	@Autowired
	public CancelationRequestReasonsService(CancelationRequestRepositoryImp cancelationRequestRepository) {

		this.cancelationRequestRepositoryImp = cancelationRequestRepository;
	}
	
	
	
	public List<CancelationRequest> finadAllReasons() {

		return cancelationRequestRepositoryImp.query();
	}

}
