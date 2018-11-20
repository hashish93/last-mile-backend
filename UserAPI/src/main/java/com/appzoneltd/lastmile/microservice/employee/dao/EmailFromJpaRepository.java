package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.EmailFromEntity;

/**
 * Repository : EmailFrom.
 */
public interface EmailFromJpaRepository extends CrudRepository<EmailFromEntity, Integer> {

}
