package com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.OrderStatus;

public class RequestHistoryRowMapper implements RowMapper<RequestHistory> {
	private RequestHistory requestHistory;

	@Override
	public RequestHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		requestHistory = new RequestHistory();
		requestHistory.setCreated(rs.getTimestamp("created"));
		requestHistory.setRequestId(rs.getLong("request_id"));
		if (rs.wasNull()){
			requestHistory.setRequestId(null);
		}
		requestHistory.setOrderStatus(OrderStatus.valueOf(rs.getString("request_status")));
		requestHistory.setPackageId(rs.getLong("package_id"));
		requestHistory.setRequestHistoryId(rs.getLong("request_history_id"));
		requestHistory.setVersion(rs.getLong("version"));
		return requestHistory;
	}

}
