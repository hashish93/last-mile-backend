package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PickupRequestDocumentEntity;

/**
 * Repository : PickupRequestDocument.
 */
public interface PickupRequestDocumentJpaRepository extends PagingAndSortingRepository<PickupRequestDocumentEntity, Long> {

}
