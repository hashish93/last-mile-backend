package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.ReturnRequestEntity;

/**
 * Repository : ReturnRequest.
 */
public interface ReturnRequestRepository extends PagingAndSortingRepository<ReturnRequestEntity, Long> {


	@Query("SELECT DISTINCT r FROM ReturnRequestEntity r inner join r.requester as requester , UsersEntity as u ,RequestHistoryEntity h inner join h.packageEntity as p inner join p.packageType as pt "
			+ "WHERE (u.userId = requester.customerId) AND r.hubId IN (:hubs) "
			+ "AND (r.returnRequestId = h.requestId)"
			+ " AND (CAST(r.returnRequestId AS string) LIKE :requestId) "
			+ " AND (LOWER(u.username) LIKE LOWER(:senderName))"
			+ " AND (u.phone LIKE :senderPhone) "
			+ " AND (LOWER(r.returnFormattedAddress) LIKE LOWER(:returnAddress)) "
			+ " AND (r.requestStatus = :packageStatus OR '' = :packageStatus) "
			+ " AND (r.returnDate BETWEEN :dateFrom AND :dateTo) "
			+ " AND (pt.packagetype LIKE :packageType) ")
	Page<ReturnRequestEntity> getReturnRequestForHubsPageable(@Param("hubs") List<Long> hubs,@Param("requestId") String requestId,@Param("senderName") String senderName ,@Param("senderPhone") String senderPhone,@Param("returnAddress") String returnAddress ,@Param("packageStatus") String packageStatus,@Param("dateFrom") Date dateFrom,@Param("dateTo") Date dateTo , @Param("packageType") String packageType ,Pageable pageable);

	@Query("SELECT DISTINCT r FROM ReturnRequestEntity r inner join r.requester as requester , UsersEntity as u ,RequestHistoryEntity h inner join h.packageEntity as p inner join p.packageType as pt "
			+ "WHERE (u.userId = requester.customerId) AND r.hubId IN (:hubs) "
			+ "AND (r.returnRequestId = h.requestId)"
			+ " AND (CAST(r.returnRequestId AS string) LIKE :requestId) "
			+ " AND (LOWER(u.username) LIKE LOWER(:senderName))"
			+ " AND (u.phone LIKE :senderPhone) "
			+ " AND (LOWER(r.returnFormattedAddress) LIKE LOWER(:returnAddress))"
			+ " AND (r.requestStatus = :packageStatus OR '' = :packageStatus) "
			+ " AND (r.returnDate BETWEEN :dateFrom AND :dateTo) "
			+ " AND (pt.packagetype LIKE :packageType) ")
	List<ReturnRequestEntity> getReturnRequestForHubsNotPageable(@Param("hubs") List<Long> hubs,@Param("requestId") String requestId,@Param("senderName") String senderName ,@Param("senderPhone") String senderPhone,@Param("returnAddress") String returnAddress ,@Param("packageStatus") String packageStatus,@Param("dateFrom") Date dateFrom,@Param("dateTo") Date dateTo , @Param("packageType") String packageType);
	
	@Query("SELECT r FROM ReturnRequestEntity r WHERE r.hubId IN (:hubs) ")
	List<ReturnRequestEntity> getReturnRequestForHubs(@Param("hubs") List<Long> hubs);

	@Query(value = "SELECT d FROM ReturnRequestEntity d WHERE d.requestStatus ='OUT_FOR_RETURN' AND d.returnRequestId=:requestId")
	public List<ReturnRequestEntity> trackedDeliveryRequest(@Param("requestId") Long requestId);
	
	
	@Query(value = "SELECT r FROM ReturnRequestEntity r WHERE r.requestStatus ='RETURNED' OR r.requestStatus ='CANCELED' AND r.hubId IN (:hubs) order by r.created DESC ")
	 List<ReturnRequestEntity> getArchivedReturns(@Param("hubs") List<Long> hubs);
	
	
	@Query(value = "SELECT r FROM ReturnRequestEntity r WHERE r.requestStatus ='RETURNED' OR r.requestStatus ='CANCELED' AND r.hubId IN (:hubs) order by r.created DESC ")
	Page <ReturnRequestEntity> getArchivedReturnsPageable(@Param("hubs") List<Long> hubs , Pageable pageable);

}
