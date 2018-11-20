package com.appzoneltd.lastmile.microservice.customer.dao;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

/**
 * @author alaa.nabil
 *
 */
@ViewIndexed(designDoc = "customerfirebasetokenrepository")
public interface CustomerFirebaseTokenRepository extends CrudRepository<CustomerFirebaseToken, Long> {
	CustomerFirebaseToken findOneByUserId(Long userId);
}
