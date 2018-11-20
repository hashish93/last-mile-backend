package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.OutOfCoverageRequestEntity;

/**
 * Repository : OutOfCoverageRequest.
 */
public interface OutOfCoverageRequestRepository extends PagingAndSortingRepository<OutOfCoverageRequestEntity, Long> {

	@Query("SELECT o FROM OutOfCoverageRequestEntity o WHERE o.created > :createdTime ORDER BY o.created DESC")
	List<OutOfCoverageRequestEntity> getLastUpdatedEntites(@Param("createdTime") Date createdTime);
}
