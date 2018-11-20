package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PackageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository : Package.
 */
public interface PackageJpaRepository extends PagingAndSortingRepository<PackageEntity, Long> {

    public String GET_FILTERED_PACKAGES = "select DISTINCT p.package_id as packageId, p.tracking_number as packageTrackingNumber , pt.packagetype , pr.recipientname as recipientName , pr.recipientmobile as recipientPhone , h.request_id as requestId , prt.type as requestStatus  , h.request_type as requestType , u.username as requesterName , u.phone as senderPhone  , h.request_status as status from lastmile_core.package AS p , lastmile_request.request_history h , lastmile_core.package_type pt , lastmile_request.pickup_request pr , lastmile_request.pickup_request_type prt , lastmile_authorization_server.users u " +
            "where p.package_id  = h.package_id AND p.status = h.request_status AND h.request_status<>'CANCELED'  AND pt.package_type_id = p.package_type_id AND pr.pickup_request_id = h.request_id AND prt.pickup_request_type_id = pr.pickup_request_type_id AND u.user_id = pr.requester_id AND (CAST(pr.pickup_request_id AS varchar(100)) LIKE :requestId) AND pr.hub_id IN (:hubs) AND (CAST(p.package_id AS varchar(100)) LIKE :packageId) AND (pt.packagetype LIKE :packageType) AND (:status = '%%' OR h.request_status LIKE :status) AND (pr.recipientname LIKE :recipientName) AND (pr.recipientmobile LIKE :recipientPhone) AND (u.username LIKE :requesterName) AND (u.phone LIKE :senderPhone) AND ((p.tracking_number IS NULL AND :packageTrackingNumber='%%')  OR p.tracking_number LIKE :packageTrackingNumber) AND (h.request_type LIKE :requestType) AND (prt.type LIKE :requestStatus)";
    public String GET_FILTERED_PACKAGES_WITH_OFFSET = "select DISTINCT p.package_id as packageId, p.tracking_number as packageTrackingNumber , pt.packagetype , pr.recipientname as recipientName , pr.recipientmobile as recipientPhone , h.request_id as requestId , prt.type as requestStatus  , h.request_type as requestType , u.username as requesterName , u.phone as senderPhone  , h.request_status as status from lastmile_core.package AS p , lastmile_request.request_history h , lastmile_core.package_type pt , lastmile_request.pickup_request pr , lastmile_request.pickup_request_type prt , lastmile_authorization_server.users u " +
            "where p.package_id  = h.package_id AND p.status = h.request_status AND h.request_status<>'CANCELED'  AND pt.package_type_id = p.package_type_id AND pr.pickup_request_id = h.request_id AND prt.pickup_request_type_id = pr.pickup_request_type_id AND u.user_id = pr.requester_id AND (CAST(pr.pickup_request_id AS varchar(100)) LIKE :requestId) AND pr.hub_id IN (:hubs) AND (CAST(p.package_id AS varchar(100)) LIKE :packageId) AND (pt.packagetype LIKE :packageType) AND (:status = '%%' OR h.request_status LIKE :status) AND (pr.recipientname LIKE :recipientName) AND (pr.recipientmobile LIKE :recipientPhone) AND (u.username LIKE :requesterName) AND (u.phone LIKE :senderPhone) AND ((p.tracking_number IS NULL AND :packageTrackingNumber='%%')  OR p.tracking_number LIKE :packageTrackingNumber) AND (h.request_type LIKE :requestType) AND (prt.type LIKE :requestStatus) LIMIT :pageSize OFFSET :offset ";

    PackageEntity findBypackageId(Long packageId);

    List<PackageEntity> findByNicknameOrTrackingNumber(String nickname, String trackingNumber);

    @Query("SELECT p FROM PackageEntity p ORDER BY p.created DESC")
    List<PackageEntity> findAllMyPackage();

    @Query(value = "select p.package_id as packageId, p.tracking_number as packageTrackingNumber , pt.packagetype , pr.recipientname as recipientName , pr.recipientmobile as recipientPhone , h.request_id as requestId , prt.type as requestStatus  , h.request_type as requestType , u.username as requesterName , u.phone as senderPhone  , h.request_status as status from lastmile_core.package AS p , lastmile_request.request_history h , lastmile_core.package_type pt , lastmile_request.pickup_request pr , lastmile_request.pickup_request_type prt , lastmile_authorization_server.users u where p.package_id  = h.package_id AND p.status = h.request_status AND h.request_status<>'CANCELED'  AND pt.package_type_id = p.package_type_id AND pr.pickup_request_id = h.request_id AND prt.pickup_request_type_id = pr.pickup_request_type_id AND u.user_id = pr.requester_id Order By p.created desc", nativeQuery = true)
    List<Object> getFilteredPackages();

//	@Modifying(clearAutomatically = true)
//	@Query(value = "UPDATE PackageEntity p SET p.packageType=:packageType, p.actualweight=:actualWeight, p.description=:description WHERE p.packageId=:id")
//	int updateDetails(@Param("packageType") PackageTypeEntity packageType, @Param("actualWeight") BigDecimal actualWeight,
//			@Param("description") String description, @Param("id") Long id);
}
