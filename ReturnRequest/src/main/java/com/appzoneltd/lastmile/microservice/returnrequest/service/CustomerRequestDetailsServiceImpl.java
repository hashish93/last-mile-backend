package com.appzoneltd.lastmile.microservice.returnrequest.service;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.repository.ReturnRequestRepository;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.CustomerDtoMapper;
import com.appzoneltd.lastmile.microservice.returnrequest.dto.CustomerRequestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
@Service
public class CustomerRequestDetailsServiceImpl implements CustomerRequestDetailsService {

    @Autowired
    private ReturnRequestRepository returnRequestRepository;
	@Autowired
	private CustomerDtoMapper dtoMapper;
	
    @Override
    public CustomerRequestDetails getRequestDetailsForCustomer(Long requestId) {
        return dtoMapper.requestToCustomerRequestDetails(returnRequestRepository.findOne(requestId));
    }

	@Override
	public boolean checkTrackedPickupRequest(Long requestId) {
		Boolean isTracked = false;
        Integer count = returnRequestRepository.trackedDeliveryRequest(requestId).size();
        
        if (count != null && count > 0)
            isTracked = true;

        return isTracked;
	}

   
}
