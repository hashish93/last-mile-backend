package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.StaticContentEntity;

/**
 * Repository : StaticContent.
 */
public interface StaticContentJpaRepository extends PagingAndSortingRepository<StaticContentEntity, Long> {

}
