package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo;


import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.RequestTypeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by hashish on 5/21/2017.
 */
public interface RequestTypeRepository extends PagingAndSortingRepository<RequestTypeEntity, Long> {

}

