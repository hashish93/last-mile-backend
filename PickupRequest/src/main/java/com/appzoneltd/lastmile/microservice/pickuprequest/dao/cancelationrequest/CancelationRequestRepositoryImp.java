package com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CancelationRequestRepositoryImp extends AbstractDao<CancelationRequest> implements CancelationRequestRepository {

    @Autowired
    public CancelationRequestRepositoryImp(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, new CancelationRequestRowMapper(), INSERT_SQL, null, null, SQL_SELECT_ALL, null, null, null);
        // TODO Auto-generated constructor stub
    }

    public String getCancellationReasonByRequestId(Long requestId) {
        String reason = null;
        try {
            reason = jdbcTemplate.queryForObject(SELECT_REASON_BY_REQUEST_ID, String.class, requestId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return reason;
    }
}
