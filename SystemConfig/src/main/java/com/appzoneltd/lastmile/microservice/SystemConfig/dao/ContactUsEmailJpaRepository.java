package com.appzoneltd.lastmile.microservice.SystemConfig.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository : ContactUsEmail.
 */
public interface ContactUsEmailJpaRepository extends CrudRepository<ContactUsEmailEntity, Long> {

	
	List<ContactUsEmailEntity> findAllByContactUsId(Long id) ;
}
