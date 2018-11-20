package com.appzoneltd.lastmile.microservice.hubconfig.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.hubconfig.entity.ContactUsEmailEntity;

/**
 * Repository : ContactUsEmail.
 */
public interface ContactUsEmailJpaRepository extends CrudRepository<ContactUsEmailEntity, Long> {

	
	List<ContactUsEmailEntity> findAllByContactUsId(Long id) ;
}
