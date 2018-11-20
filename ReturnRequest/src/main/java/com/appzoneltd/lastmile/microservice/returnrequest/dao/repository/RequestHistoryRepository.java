package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;


import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.RequestHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryRepository extends CrudRepository<RequestHistoryEntity, Long> {

    List<RequestHistoryEntity> findByRequestId(Long requestId);

    @Query(value = "SELECT * FROM lastmile_request.request_history WHERE request_status<>'' AND request_type='RETURN' AND request_id=:requestId ORDER BY created ASC ", nativeQuery = true)
    List<RequestHistoryEntity> listRequestHistoryTimeLine(@Param("requestId") Long requestId);

    @Query(value = "SELECT r FROM RequestHistoryEntity r WHERE r.requestStatus<>''  AND packageEntity.packageId=:packageId ORDER BY created ASC ")
    List<RequestHistoryEntity> listRequestHistoryTimeLineByPackage(@Param("packageId") Long packageId);

    @Query(value = "SELECT DISTINCT p.packageId FROM RequestHistoryEntity h inner join h.packageEntity p WHERE h.requestType='RETURN' AND h.requestId=:requestId")
    Long getPackageId(@Param("requestId") Long requestId);

    long countAllByRequestTypeAndRequestIdAndRequestStatusIn(String requestType, Long requestId, List<String> requestStatus);
}
