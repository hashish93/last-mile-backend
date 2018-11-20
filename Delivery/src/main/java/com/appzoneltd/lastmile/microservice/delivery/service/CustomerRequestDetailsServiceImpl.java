package com.appzoneltd.lastmile.microservice.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.delivery.dao.repository.DeliveryRequestRepository;
import com.appzoneltd.lastmile.microservice.delivery.dto.CustomerDtoMapper;
import com.appzoneltd.lastmile.microservice.delivery.dto.CustomerRequestDetails;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
@Service
public class CustomerRequestDetailsServiceImpl implements CustomerRequestDetailsService {

	@Autowired
	private DeliveryRequestRepository deliveryRequestRepository;
	
	@Autowired
	private CustomerDtoMapper dtoMapper;
	
    @Override
    public CustomerRequestDetails getRequestDetailsForCustomer(Long requestId) {
        return dtoMapper.requestToCustomerRequestDetails(deliveryRequestRepository.findOne(requestId));
    }

   
}
