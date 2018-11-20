package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;


import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.PickupRequestEntity;

@Repository
public interface PickupRequestRepository extends JpaRepository<PickupRequestEntity, Long> {



//    @Query("SELECT new com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse(age, count(p)) FROM  com.appzoneltd.lastmile.microservices.stats.entity.PickupRequestEntity p WHERE p.hubId=:hubId AND p.pickupdate BETWEEN :from AND :to GROUP BY (now()- p.customerEntity.birthdate) AS age")
//    public List<StatsResponse> countCustomersRequestsByHubIdGroupedByAge();
	
	
	
	@Query("SELECT COUNT(x) from PickupRequestEntity x  WHERE x.requestTypeId = 1 AND x.pickupdate between :startDate and :endDate  and x.hubId IN (:hubIds)")
	public Long countOndemadRequest(@Param("startDate") Date startDate, @Param("endDate") Date endDate,@Param("hubIds") Set<Long> hubIds);


}
