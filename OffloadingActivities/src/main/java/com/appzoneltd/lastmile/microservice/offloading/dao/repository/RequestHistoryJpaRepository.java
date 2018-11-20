package com.appzoneltd.lastmile.microservice.offloading.dao.repository;


import com.appzoneltd.lastmile.microservice.offloading.dao.entity.RequestHistoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryJpaRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {
    List<RequestHistoryEntity> findByRequestIdAndRequestType(@Param("requestId") Long requestId, @Param("requestType") String requestType);
}
