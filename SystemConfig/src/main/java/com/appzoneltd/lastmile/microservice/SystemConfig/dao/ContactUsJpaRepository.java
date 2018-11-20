package com.appzoneltd.lastmile.microservice.SystemConfig.dao;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository : ContactUs.
 */
public interface ContactUsJpaRepository extends CrudRepository<ContactUsEntity, Integer> {
	
	ContactUsEntity findByLanguage(String language);
	

}
