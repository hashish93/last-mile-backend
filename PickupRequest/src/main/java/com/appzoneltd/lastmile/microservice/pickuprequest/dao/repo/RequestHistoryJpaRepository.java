package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.RequestHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : RequestHistory.
 */
public interface RequestHistoryJpaRepository extends PagingAndSortingRepository<RequestHistoryEntity, Long> {

    @Query("SELECT r FROM RequestHistoryEntity r WHERE r.requestId=:id ORDER BY r.created DESC")
    List<RequestHistoryEntity> findByRequestId(@Param("id") Long requestId);

    @Query("SELECT r FROM RequestHistoryEntity r WHERE r.packageEntity.packageId=:id ORDER BY r.created DESC")
    List<RequestHistoryEntity> findByPackageId(@Param("id") Long packageId);

    @Query(value = "SELECT r FROM RequestHistoryEntity r WHERE r.requestStatus<>''  AND packageEntity.packageId=:packageId ORDER BY created ASC ")
    List<RequestHistoryEntity> listRequestHistoryTimeLine(@Param("packageId") Long packageId);

}
