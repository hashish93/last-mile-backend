package com.appzoneltd.lastmile.microservices.devices.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservices.devices.entity.BrandEntity;

public interface BrandDao extends CrudRepository<BrandEntity, Long> {

}
