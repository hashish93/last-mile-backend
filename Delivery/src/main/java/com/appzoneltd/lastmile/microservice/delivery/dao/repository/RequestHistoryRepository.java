package com.appzoneltd.lastmile.microservice.delivery.dao.repository;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.RequestHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryRepository extends CrudRepository<RequestHistoryEntity, Long> {
    List<RequestHistoryEntity> findByRequestId(Long requestId);

    @Query(value = "SELECT * FROM lastmile_request.request_history WHERE request_status<>'' AND request_type='DELIVERY' AND request_id=:requestId ORDER BY created ASC ", nativeQuery = true)
    List<RequestHistoryEntity> listRequestHistoryTimeLine(@Param("requestId") Long requestId);


    @Query(value = "SELECT r FROM RequestHistoryEntity r WHERE r.requestStatus<>''  AND packageEntity.packageId=:packageId ORDER BY created ASC ")
    List<RequestHistoryEntity> listRequestHistoryTimeLineByPackage(@Param("packageId") Long packageId);
    
    
    @Query(value = "SELECT DISTINCT request_id FROM lastmile_request.request_history WHERE  request_type='DELIVERY' AND package_id=:packageId ", nativeQuery = true)
    Long getPackageDeliveryId(@Param("packageId") Long packageId);
}
