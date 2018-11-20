package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.DeliveryRequestEntity;

public interface DeliveryStatsJPARepository  extends JpaRepository<DeliveryRequestEntity , Long> {

	
	@Query("SELECT COUNT(x) from DeliveryRequestEntity x WHERE x.deliverydate between ?1 and ?2")
	public Long fetchDeliveriesCount(Date startDate , Date endDate);

	@Query("SELECT COUNT(x) from DeliveryRequestEntity x WHERE x.hubId = ?3 and x.deliverydate between ?1 and ?2")
	public Long fetchDeliveriesCountByHub(Date startDate, Date endDate, Long hubId);
}
