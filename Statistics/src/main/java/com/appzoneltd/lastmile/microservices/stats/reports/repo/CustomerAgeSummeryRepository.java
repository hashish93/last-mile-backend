package com.appzoneltd.lastmile.microservices.stats.reports.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.stats.reports.entity.CustomerAgeSummeryEntity;

/**
 * Repository : CustomerAgeSummery.
 */
public interface CustomerAgeSummeryRepository extends JpaRepository<CustomerAgeSummeryEntity, Long> {

//	@Query("select ageLessThan21 from CustomerAgeSummeryEntity c where c.withinPeriod=:withinPeriod")
//	Long findAllCustomersAgeLessThan21(@Param("withinPeriod") String withinPeriod);
//	
//	@Query("select  ageBetween21And30 from CustomerAgeSummeryEntity c where c.withinPeriod=:withinPeriod")
//	Long findAllCustomersAgeBetween21And30(@Param("withinPeriod") String withinPeriod);
//	
//	@Query("select  ageBetween31And45 from CustomerAgeSummeryEntity c where c.withinPeriod=:withinPeriod")
//	Long findAllCustomersAgeBetween31And45(@Param("withinPeriod") String withinPeriod);
//	
//	@Query("select  others from CustomerAgeSummeryEntity c where c.withinPeriod=:withinPeriod")
//	Long findAllCustomerOtherAges(@Param("withinPeriod") String withinPeriod);
	
	
	
}
