package com.appzoneltd.lastmile.microservice.customer.service;

import com.appzoneltd.lastmile.microservice.customer.dao.CustomerFirebaseToken;
import com.appzoneltd.lastmile.microservice.customer.model.FirebaseTokenObject;

import java.security.Principal;

/**
 * @author alaa.nabil
 *
 */
public interface CustomerFirebaseTokenService {
	CustomerFirebaseToken getCustomerToken(Long userId);

	CustomerFirebaseToken saveOrUpdateCustomerToken(FirebaseTokenObject customerFirebaseToken, Principal principal);

    boolean removeToken(String firebaseToken, Principal principal);
}
