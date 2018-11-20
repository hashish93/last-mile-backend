/**
 *
 */
package com.appzoneltd.lastmile.microservice.customer.service;

import java.security.Principal;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.customer.dao.CustomerFirebaseToken;
import com.appzoneltd.lastmile.microservice.customer.dao.CustomerFirebaseTokenRepository;
import com.appzoneltd.lastmile.microservice.customer.dto.CustomerRepositoryImp;
import com.appzoneltd.lastmile.microservice.customer.model.FirebaseTokenObject;

/**
 * @author alaa.nabil
 */
@Service
public class CustomerFirebaseTokenServiceImp implements CustomerFirebaseTokenService {

	private final CustomerFirebaseTokenRepository customerFirebaseTokenRepository;
	private final CustomerRepositoryImp customerRepository;
//	private final String DEFAULT_NOTIFICATION_TYPE;
	
	@Autowired
	private PrincipalService principalService;
	
    @Value("${notification.default-type}")
    private String DEFAULT_NOTIFICATION_TYPE;

	/**
	 * @param customerFirebaseTokenRepository
	 */
	@Autowired
	public CustomerFirebaseTokenServiceImp(CustomerFirebaseTokenRepository customerFirebaseTokenRepository,
			CustomerRepositoryImp customerRepository ) {
		
		this.customerFirebaseTokenRepository = customerFirebaseTokenRepository;
		this.customerRepository = customerRepository;
//		this.DEFAULT_NOTIFICATION_TYPE = DEFAULT_NOTIFICATION_TYPEI;
	}

	@PostConstruct
	public void x() {
		System.out.println("************&&&&&&&&&&&& "+DEFAULT_NOTIFICATION_TYPE);
	}
	@Override
	public CustomerFirebaseToken getCustomerToken(Long userId) {
		return customerFirebaseTokenRepository.findOneByUserId(userId);
	}

	@Override
	public CustomerFirebaseToken saveOrUpdateCustomerToken(FirebaseTokenObject firebaseTokenObject,
			Principal principal) {
		Long userId = principalService.getUserId(principal);
		
		if (firebaseTokenObject == null || 
				firebaseTokenObject.getFirebaseToken().trim() == null ||
				firebaseTokenObject.getFirebaseToken().trim().isEmpty()) {
			
			return null;
		}
		if (firebaseTokenObject.getType() == null || 
				firebaseTokenObject.getType().trim().isEmpty()) {
			
			firebaseTokenObject.setType(DEFAULT_NOTIFICATION_TYPE);
		}
		
		CustomerFirebaseToken customerFirebaseToken = customerFirebaseTokenRepository.findOneByUserId(userId);
		
		if (customerFirebaseToken != null) {
			customerFirebaseToken.getFirebaseTokens().add(firebaseTokenObject);
		}
		else {
			customerFirebaseToken = new CustomerFirebaseToken(userId, userId, Collections.singleton(firebaseTokenObject));
		}

		return customerFirebaseTokenRepository.save(customerFirebaseToken);
	}

	@Override
	public boolean removeToken(String firebaseToken, Principal principal) {
		Long userId = principalService.getUserId(principal);
		CustomerFirebaseToken customerFirebaseToken = customerFirebaseTokenRepository.findOneByUserId(userId);

		if (firebaseToken == null || firebaseToken.trim().isEmpty()) {
			return false ;
		}
		if (customerFirebaseToken != null) {
			customerFirebaseToken.removeFirebaseToken(firebaseToken);
			
			//after removing the firebasetoken if exists: if there are no any firebase tokens .. then remove the entire object from couchbase to save memory
			if (customerFirebaseToken.getFirebaseTokens().isEmpty()) {
				customerFirebaseTokenRepository.delete(customerFirebaseToken);
			} else {
				customerFirebaseTokenRepository.save(customerFirebaseToken);
			}
			return true;
		}
		return false;
	}

	private Long getUserId(String principal) {
		return customerRepository.getAuthonticatedUserId(principal);
	}

}
