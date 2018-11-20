package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.StaticContentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Repository : StaticContent.
 */
public interface StaticContentJpaRepository extends PagingAndSortingRepository<StaticContentEntity, Long> {

}
