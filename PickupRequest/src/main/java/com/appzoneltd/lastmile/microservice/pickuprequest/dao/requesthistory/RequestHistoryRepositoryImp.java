package com.appzoneltd.lastmile.microservice.pickuprequest.dao.requesthistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

@Repository
public class RequestHistoryRepositoryImp extends AbstractDao<RequestHistory> implements RequestHistoryRepository {

	@Autowired
	public RequestHistoryRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new RequestHistoryRowMapper(), SQL_INSERT, SQL_UPDATE, null, SQL_SELECT_ALL, SQL_SELECT,
				SQL_DELETE, SQL_COUNT_ALL);
	}


	
	
	public List<RequestHistory> getAllRequestHistoryDetail (Long requestId){
		
		List<RequestHistory> info =jdbcTemplate.query(SQL_SELECT_REQUEST_STATUS, new RequestTimeLineRowMapper(),requestId);
		return info ;
	}
	
	
	public Long findRequestId(Long packageId) {

		return jdbcTemplate.queryForObject(SQL_SELECT_REQUEST_ID, Long.class, packageId);
	}

	
}
