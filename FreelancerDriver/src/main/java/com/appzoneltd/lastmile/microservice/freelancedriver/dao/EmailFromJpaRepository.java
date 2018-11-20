package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.EmailFromEntity;

/**
 * Repository : EmailFrom.
 */
public interface EmailFromJpaRepository extends CrudRepository<EmailFromEntity, Integer> {

}
