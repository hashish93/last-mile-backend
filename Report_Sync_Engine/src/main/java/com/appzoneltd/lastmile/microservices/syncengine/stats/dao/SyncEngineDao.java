package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;


public interface SyncEngineDao extends CrudRepository<SyncEngineEntity, Long> {

}
