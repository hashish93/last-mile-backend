package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.ReturnRequestEntity;

public interface ReturnStatusJPARepository extends JpaRepository<ReturnRequestEntity, Long>{

	
	@Query("SELECT COUNT(x) from ReturnRequestEntity x WHERE x.returnDate between ?1 and ?2")
	public Long fetchReturnsCount(Date startDate , Date endDate);

	@Query("SELECT COUNT(x) from ReturnRequestEntity x WHERE x.hubId = ?3 and x.returnDate between ?1 and ?2")
	public Long fetchReturnsCountByHub(Date startDate, Date endDate, Long hubId);
}
