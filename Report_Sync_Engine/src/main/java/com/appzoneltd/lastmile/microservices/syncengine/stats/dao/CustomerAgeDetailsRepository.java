package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.CustomerAgeSummeryReportModel;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.CustomerAgeDetailsEntity;

/**
 * Repository : CustomerAgeDetails.
 */
public interface CustomerAgeDetailsRepository extends PagingAndSortingRepository<CustomerAgeDetailsEntity, Long> {

	CustomerAgeDetailsEntity findByCustomerId(Long customerId);
	
	@Query("SELECT new com.appzoneltd.lastmile.microservices.syncengine.engine.model.CustomerAgeSummeryReportModel (c.customerAge AS age , count(c) AS count)  FROM CustomerAgeDetailsEntity  c WHERE c.created BETWEEN  :pastToday AND :today  group by c.customerAge")
	 List<CustomerAgeSummeryReportModel>  findByAgeGroupedByCreateDate(@Param("today") Date today , @Param("pastToday") Date pastDate);
	
	
	@Query("SELECT new com.appzoneltd.lastmile.microservices.syncengine.engine.model.CustomerAgeSummeryReportModel (c.customerAge AS age , count(c) AS count)  FROM CustomerAgeDetailsEntity  c  group by c.customerAge")
	 List<CustomerAgeSummeryReportModel>  findByAgeGrouped();
}
