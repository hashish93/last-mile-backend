package com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CancelationRequestRowMapper implements RowMapper<CancelationRequest> {

	private CancelationRequest cancelationRequest ;
	@Override
	public CancelationRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		cancelationRequest = new CancelationRequest();
		
		cancelationRequest.setReasonId(rs.getLong("id"));
		cancelationRequest.setReason(rs.getString("reason"));
		
		return cancelationRequest;
	}
	

}
