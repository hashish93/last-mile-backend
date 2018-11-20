package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.DriverVerificationEntity;



public interface DriverVerificationRepository extends PagingAndSortingRepository<DriverVerificationEntity, Long> {

}
