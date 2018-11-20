package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.SavePlanOrderEntity;

@Repository
public interface SavePlanOrderRepo extends CrudRepository<SavePlanOrderEntity,Long>{
	
}
