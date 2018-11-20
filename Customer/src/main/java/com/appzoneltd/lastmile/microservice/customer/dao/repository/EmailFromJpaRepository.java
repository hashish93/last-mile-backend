package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.EmailFromEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository : EmailFrom.
 */
public interface EmailFromJpaRepository extends CrudRepository<EmailFromEntity, Integer> {

}
