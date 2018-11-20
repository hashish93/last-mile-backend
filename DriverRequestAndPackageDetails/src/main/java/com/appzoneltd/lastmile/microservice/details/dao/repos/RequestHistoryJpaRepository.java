package com.appzoneltd.lastmile.microservice.details.dao.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.details.dao.entity.RequestHistoryEntity;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryJpaRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {

    @Query("SELECT r FROM RequestHistoryEntity r WHERE r.requestId=:id ORDER BY r.created DESC")
    List<RequestHistoryEntity> findByRequestId(@Param("id") Long requestId);
    
    
    @Query("SELECT r FROM RequestHistoryEntity r WHERE r.requestId=:id and r.requestType=:orderType ORDER BY r.created DESC")
    List<RequestHistoryEntity> findByRequestIdOfGivenType(@Param("id") Long requestId , @Param("orderType") String orderType);
}
