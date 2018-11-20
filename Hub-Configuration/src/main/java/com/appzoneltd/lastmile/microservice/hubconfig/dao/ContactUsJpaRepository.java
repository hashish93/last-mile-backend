package com.appzoneltd.lastmile.microservice.hubconfig.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.hubconfig.entity.ContactUsEntity;

/**
 * Repository : ContactUs.
 */
public interface ContactUsJpaRepository extends CrudRepository<ContactUsEntity, Integer> {
	
	ContactUsEntity findByLanguage(String language);
	

}
