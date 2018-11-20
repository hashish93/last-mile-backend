package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.DeliveryRequestRowMapper;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory.ReturnRequestRowMapper;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.*;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Repository
public class PickupRequestRepositoryImp extends AbstractDao<PickupRequestInfo> implements PickupRequestRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PickupRequestRepositoryImp(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, new PickupRequestRowMapper(), SQL_INSERT, null, null, null, SQL_SELECT_BY_ID,
                DELETE_PICKUP_REQUEST, null);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public PackagePickupLocation getPackageLocation(Long packageId) {

        List<PackagePickupLocation> packagePickupLocations = jdbcTemplate.query(SELECT_PACKAGE_REQUEST_LOCATION,
                new PackagePickupLocationRowMapper(), packageId);

        PackagePickupLocation pickupLocation = new PackagePickupLocation();

        if (packagePickupLocations.size() > 0) {
            pickupLocation = packagePickupLocations.get(0);
            // return result
            return pickupLocation;
        }

        return null;
    }

    public PackagePickupRequestInfo getPackagePickupRequestInfo(Long packageId) {

        List<PackagePickupRequestInfo> pickupRequestInfos = jdbcTemplate.query(SELECT_PACKAGE_REQUEST_INFO,
                new PackagePickupRequestInfoRowMapper(), packageId);

        PackagePickupRequestInfo pickupRequestInfo = new PackagePickupRequestInfo();

        if (pickupRequestInfos.size() > 0) {
            pickupRequestInfo = pickupRequestInfos.get(0);
            // return result
            return pickupRequestInfo;
        }

        return null;
    }

    public PickupRequestData getPickupRequestByPackage(Long packageId) {
        // Gettign PackageStatusRequestByPackage
        List<PickupRequestData> pickupRequestDatas = jdbcTemplate.query(SELECT_REQUEST_BY_PACKAGE_ID,
                new PickupRequestDataRowMapper(), packageId);
        // Init new PickupRequestData
        PickupRequestData pickupRequestData = new PickupRequestData();
        // Check if null
        if (pickupRequestDatas.size() > 0) {
            pickupRequestData = pickupRequestDatas.get(0);
            // return result
            return pickupRequestData;
        } // end if Condition
        return null;
    }// end getPickupRequestByPackage

    /// Getting PackageOrder Info
    public PackageOrderInfoModel getPackageOrderInfo(Long packageId) {
        List<PackageOrderInfoModel> packageOrderInfoModels = jdbcTemplate.query(SELECT_PACKAGE_ORDER_INFO,
                new PackageOrderInfoModelRowMapper(), packageId);
        // Init new PickupRequestData
        PackageOrderInfoModel packageOrderInfoModel = new PackageOrderInfoModel();
        // Check if null
        if (packageOrderInfoModels.size() > 0) {
            packageOrderInfoModel = packageOrderInfoModels.get(0);
            // return result
            return packageOrderInfoModel;
        } // end if Condition
        return null;
    }// end getPickupRequestByPackage

    public void getWorkflowPackageInfo(Long packageId) {

    }

    public int updatePickupRequestStatus(Long requestId, String status) {
        return jdbcTemplate.update(UPDATE_REQUEST_STATUS, status, requestId);
    }

    public int updatePickupRequestStatusWithReason(Long requestId, String status, String cancelReason) {
        return jdbcTemplate.update(UPDATE_REQUEST_STATUS_WITH_REASON, status, cancelReason, requestId);
    }

    public int assignRequestToHub(Long requestId, Long hubId) {
        return jdbcTemplate.update(ASSIGN_PACKAGE_TO_HUB, hubId, requestId);
    }// end assignPackageToHub

    public List<PickupRequestInfo> findAllScheduledRequestsByPage(int page, int maxResult, OrderBy orderType,
                                                                  Long requestId, String requesterMobile, String fromRequestDateStr, String toRequestDateStr,
                                                                  String fromPickupDateStr, String toPickupDateStr, List<Long> hubIds) {
        StringBuilder sqlQuery = buildQuery(PickupRequestRepository.SQL_SELECT_ALL_BY_REQUEST_TYPE, requestId,
                requesterMobile, fromRequestDateStr, toRequestDateStr, fromPickupDateStr, toPickupDateStr);
        sqlQuery.append("AND pickup_request.request_status<>:status ");
        sqlQuery.append("ORDER BY pickup_request.created ");

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("type", getRequestTypeId("SCHEDULED"));
        parameterSource.addValue("hubs", hubIds);
        parameterSource.addValue("status", OrderStatus.CANCELED.getOrderStatus());

        return namedParameterJdbcTemplate.query(
                queryBuilderWithPageAndOffset(sqlQuery.toString(), page, maxResult, orderType), parameterSource, new PickupRequestRowMapper());
    }

    private StringBuilder buildQuery(String query, Long requestId, String requesterMobile, String fromRequestDateStr,
                                     String toRequestDateStr, String fromPickupDateStr, String toPickupDateStr) {
        StringBuilder sqlQuery = new StringBuilder(query);

        if (requestId != null) {
            sqlQuery.append("AND pickup_request_id=");
            sqlQuery.append(requestId);
            sqlQuery.append(" ");
        }

        if (requesterMobile != null && !requesterMobile.equalsIgnoreCase("")) {
            sqlQuery.append("AND users.phone=");
            sqlQuery.append("'");
            sqlQuery.append(requesterMobile);
            sqlQuery.append("'");
            sqlQuery.append(" ");
        }

        if (fromRequestDateStr != null && !fromRequestDateStr.equalsIgnoreCase("")) {
            sqlQuery.append("AND requesttime>=");
            sqlQuery.append("'");
            sqlQuery.append(fromRequestDateStr);
            sqlQuery.append("'");
            sqlQuery.append(" ");
        }

        if (toRequestDateStr != null && !toRequestDateStr.equalsIgnoreCase("")) {
            sqlQuery.append("AND requesttime<=");
            sqlQuery.append("'");
            sqlQuery.append(toRequestDateStr);
            sqlQuery.append("'");
            sqlQuery.append(" ");
        }

        if (fromPickupDateStr != null && !fromPickupDateStr.equalsIgnoreCase("")) {
            sqlQuery.append("AND pickupdate>=");
            sqlQuery.append("'");
            sqlQuery.append(fromPickupDateStr);
            sqlQuery.append("'");
            sqlQuery.append(" ");
        }

        if (toPickupDateStr != null && !toPickupDateStr.equalsIgnoreCase("")) {
            sqlQuery.append("AND pickupdate<=");
            sqlQuery.append("'");
            sqlQuery.append(toPickupDateStr);
            sqlQuery.append("'");
            sqlQuery.append(" ");
        }

        return sqlQuery;
    }

    public Long getRequestTypeId(String requestType) {
        return jdbcTemplate.queryForObject(SELECT_PICKUP_TYPE_ID, Long.class, "%" + requestType + "%");
    }

    private String queryBuilderWithPageAndOffset(String sql, int page, int pageSize, OrderBy orderBy) {
        StringBuilder sqlQuery = new StringBuilder(sql);
        if (orderBy != null)
            sqlQuery.append(orderBy.getOrderBy() + " ");

        if (page > 0) {
            int offset = (page - 1) * pageSize;
            sqlQuery.append("LIMIT ");
            sqlQuery.append(pageSize);
            sqlQuery.append(" OFFSET ");
            sqlQuery.append(offset);
        }
        return sqlQuery.toString();
    }

    public int updateScheduledPickupTimeAndDate(String timeFrom, String timeTo, Date pickupDate, Long pickupRequestId) {
        return jdbcTemplate.update(UPDATE_SCHEDUED_REQUEST_PICKUP_DATE, timeFrom, timeTo, pickupDate, pickupRequestId);
    }

    public int cancelScheduledRequest(Long scheduledRequestId) {
        return jdbcTemplate.update(CANCEL_SCHEDULED_REQUEST, OrderStatus.CANCELED.getOrderStatus(), scheduledRequestId);
    }

    public int countAllScheduledRequest(Long requestId, String requesterMobile, String fromRequestDateStr,
                                        String toRequestDateStr, String fromPickupDateStr, String toPickupDateStr, List<Long> hubs) throws ParseException {

        StringBuilder sqlQuery = buildQuery(SQL_COUNT_ALL_BY_REQUEST_TYPE, requestId, requesterMobile,
                fromRequestDateStr, toRequestDateStr, fromPickupDateStr, toPickupDateStr);

        sqlQuery.append("AND pickup_request.request_status<>:status ");

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("type", getRequestTypeId("SCHEDULED"));
        parameterSource.addValue("hubs", hubs);
        parameterSource.addValue("status", OrderStatus.CANCELED.getOrderStatus());

        return namedParameterJdbcTemplate.queryForObject(sqlQuery.toString(), parameterSource, Integer.class);
    }

    public List<PickupRequestInfo> findTakenOnDemand(OrderBy orderBy, List<Long> hubIds) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("type", getRequestTypeId("ON-DEMAND"));
        parameterSource.addValue("hubs", hubIds);
        parameterSource.addValue("status1", OrderStatus.NEW.getOrderStatus());
        parameterSource.addValue("status2", OrderStatus.ACTION_NEEDED.getOrderStatus());
        parameterSource.addValue("status3", OrderStatus.WAITING_FOR_CUSTOMER_ALTERNATIVE.getOrderStatus());
        parameterSource.addValue("status4", OrderStatus.ASSIGNED.getOrderStatus());
        return namedParameterJdbcTemplate.query(buildOndemandTakenQuery(orderBy), parameterSource
                , new PickupRequestRowMapper());
    }

    private String buildOndemandTakenQuery(OrderBy orderBy) {
        StringBuilder stringBuilder = new StringBuilder(SQL_SELECT_ALL_BY_REQUEST_TYPE);
        stringBuilder
                .append("AND (pickup_request.request_status =:status1 OR pickup_request.request_status =:status2 OR pickup_request.request_status =:status3 OR pickup_request.request_status =:status4) ORDER BY pickup_request.created "
                        + orderBy.getOrderBy());
        return stringBuilder.toString();
    }

    private String serToSQLString(List<Long> hubIds) {
        final StringBuilder stringBuilder = new StringBuilder("(");
        for (int l = 0; l < hubIds.size(); l++) {
            stringBuilder.append(l);
            if (l != hubIds.size() - 1)
                stringBuilder.append(",");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public List<PickupRequestInfo> findUntakenOnDemand(OrderBy orderBy, List<Long> hubIds) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("type", getRequestTypeId("ON-DEMAND"));
        parameterSource.addValue("hubs", hubIds);
        parameterSource.addValue("status1", OrderStatus.AWAITING_PICKUP.getOrderStatus());
        parameterSource.addValue("status2", OrderStatus.IN_PICKUP_VERIFICATION.getOrderStatus());
        parameterSource.addValue("status3", OrderStatus.CANCELED.getOrderStatus());
        parameterSource.addValue("status4", OrderStatus.PICKEDUP.getOrderStatus());

        List<PickupRequestInfo> onDemandRequest = namedParameterJdbcTemplate.query(buildOndemandUnTakenQuery(orderBy), parameterSource
                , new PickupRequestRowMapper());
        return onDemandRequest;
    }

    private String buildOndemandUnTakenQuery(OrderBy orderBy) {
        StringBuilder stringBuilder = new StringBuilder(SQL_SELECT_ALL_BY_REQUEST_TYPE);
        stringBuilder
                .append("AND (pickup_request.request_status =:status1 OR pickup_request.request_status =:status2 OR (pickup_request.request_status =:status3 AND pickupdate = current_date) OR (pickup_request.request_status =:status4 AND pickupdate = current_date)) ORDER BY pickup_request.created "
                        + orderBy.getOrderBy());
        return stringBuilder.toString();
    }

    public int countAllOnDemandRequest(List<Long> hubs) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("type", getRequestTypeId("ON-DEMAND"));
        parameterSource.addValue("hubs", hubs);
        return namedParameterJdbcTemplate.queryForObject(SQL_COUNT_ALL_BY_REQUEST_TYPE, parameterSource, Integer.class);
    }

    public List<PickupRequestInfo> findAllHistoryRequests(int page, int maxResult, OrderBy orderType, List<Long> hubIds) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("type1", getRequestTypeId("ON-DEMAND"))
                .addValue("type2", getRequestTypeId("SCHEDULED"))
                .addValue("hubs", hubIds)
                .addValue("status1", OrderStatus.CANCELED.getOrderStatus())
                .addValue("status2", OrderStatus.PICKEDUP.getOrderStatus());
        return namedParameterJdbcTemplate.query(
                queryBuilderWithPageAndOffset(SQL_SELECT_ALL_HISTORY_BY_REQUEST_TYPE, page, maxResult, orderType), parameterSource,
                new RequestHistoryRowMapper());
    }

    public int countAllHistoryRequest(List<Long> hubs) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("type1", getRequestTypeId("ON-DEMAND"))
                .addValue("type2", getRequestTypeId("SCHEDULED"))
                .addValue("hubs", hubs)
                .addValue("status1", OrderStatus.CANCELED.getOrderStatus())
                .addValue("status2", OrderStatus.PICKEDUP.getOrderStatus());
        return namedParameterJdbcTemplate.queryForObject(SQL_COUNT_ALL_HISTORY_BY_REQUEST_TYPE, parameterSource, Integer.class);
    }

    public List<MyPickupRequestDTO> findAllRequestByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_REQUESTS_FOR_USER, new MyPickupRequestDTO(), userId);
    }

    public List<MyPickupRequestDTO> findAllDeliveryRequestByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_DELIVERY_REQUESTS_FOR_USER, new DeliveryRequestRowMapper(), userId);
    }

    public List<MyPickupRequestDTO> findAllReturnRequestByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_RETURN_REQUESTS_FOR_USER, new ReturnRequestRowMapper(), userId);
    }

    public Long getRequestId(Long packageId) {
        return jdbcTemplate.queryForObject(SELECT_REQUEST_ID_BY_PACKAGE_ID, Long.class, packageId);
    }

    public int ServiceRating(Long requestId, String requestType, Double rating) {
        Long serviceRatingId = Utils.generateUUID();
        return jdbcTemplate.update(INSERT_INTO_SERVICE_RATING, serviceRatingId, requestId, requestType, rating);

    }

    public int DriverRating(Long driverId, Long requestId, String requestType, Double rating) {
        Long driverRatingId = Utils.generateUUID();
        return jdbcTemplate.update(INSERT_INTO_DRIVER_RATING, driverRatingId, driverId, requestId, requestType, rating);

    }

    public int countDriverRating(Long driverId) {

        return jdbcTemplate.queryForObject(COUNT_NUMBER_OF_RATING_DRIVER, Integer.class, driverId);
    }

    public double sumDriverRating(Long driverId) {
        return jdbcTemplate.queryForObject(SUM_RATING_FOR_DRIVER, double.class, driverId);

    }

    public int updateDriverRating(double rating, Long driverId) {
        return jdbcTemplate.update(UPDATE_DRIVER_RATING, rating, driverId);
    }

    public int cancelRequest(Long RequestId) {
        return jdbcTemplate.update(CANCEL_REQUEST, OrderStatus.CANCELED.getOrderStatus(), RequestId);
    }

    public Integer queryCountOfTrackedPickupRequest(Long requestId) {
        return jdbcTemplate.queryForObject(SELECT_COUNT_REQUESTS_WITH_SPECIFIC_STATUSES, Integer.class, requestId);
    }

    public int updateFreelancerDriverRating(double rating, Long driverId) {
        return jdbcTemplate.update(UPDATE_FREELANCER_DRIVER_RATING, rating, driverId);
    }
}
