package com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.OrderStatus;

public class RequestTimeLineRowMapper implements RowMapper<RequestHistory> {
	
	private RequestHistory requestHistory;
	
	@Override
	public RequestHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		requestHistory = new RequestHistory();
		requestHistory.setOrderStatus(OrderStatus.valueOf(rs.getString("request_status")));
		requestHistory.setCreated(rs.getTimestamp("created"));
		
	
		return requestHistory;
	}


}
