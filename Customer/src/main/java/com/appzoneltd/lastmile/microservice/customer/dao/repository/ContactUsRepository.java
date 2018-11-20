package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.ContactUsEntity;

/**
 * Repository : City.
 */
public interface ContactUsRepository extends CrudRepository<ContactUsEntity, Long> {

	List<ContactUsEntity> findByLanguage(String lang);
}
