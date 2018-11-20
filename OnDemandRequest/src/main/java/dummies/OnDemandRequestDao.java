/**
 * AppZone LTD
 * Author: Amir Serry
 * Project Name OnDemandRequest
 * May 22, 2016 1:11:21 PM
 */
package dummies;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.core.AbstractDao;
import com.appzoneltd.lastmile.enums.OrderBy;

/**
 * @author Mahmoud Farahat
 *
 */
@Component
public class OnDemandRequestDao extends AbstractDao  implements OnDemandRequestDaoInfo{

	

	public List<OnDemandRequest> findTakenOnDemand(long companyId, OrderBy orderBy) {
		String takenSql = SQL_SELECT_TAKEN;
		List<OnDemandRequest> onDemandRequest = queryWithPaging(takenSql, companyId, new OnDemandRequest(), 0, 0,
				orderBy);
		return onDemandRequest;
	}

	public List<OnDemandRequest> findUntakenOnDemand(long companyId, OrderBy orderBy) {
		String untakenSql = SQL_SELECT_UNTAKEN;
		List<OnDemandRequest> onDemandRequest = queryWithPaging(untakenSql, companyId, new OnDemandRequest(), 0, 0,
				orderBy);
		return onDemandRequest;
	}

	/**
	 * May 22, 2016
	 * 
	 * @param companyId
	 * @param pageCount
	 * @param pageNum
	 * @param orderType
	 * @return ArrayList<OnDemandRequest>
	 */
	public List<OnDemandRequest> findAllOnDemandRequest(long companyId, OrderBy orderBy) {
		String selectSql = SQL_SELECT_ALL;
		List<OnDemandRequest> onDemandRequest = queryWithPaging(selectSql, companyId, new OnDemandRequest(), 0, 0,
				orderBy);
		return onDemandRequest;
	}

	/**
	 * May 22, 2016
	 * 
	 * @param id
	 * @param companyId
	 * @return OnDemandRequest
	 */
	public OnDemandRequest findOnDemandRequest(long id, long companyId) {
		String selectByIdSql = SELECT_BY_ID_SQL + id;
		try {
			OnDemandRequest onDemandRequest = (OnDemandRequest) queryForObject(selectByIdSql, companyId,
					new OnDemandRequest());
			return onDemandRequest;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	/**
	 * May 22, 2016
	 * 
	 * @param companyId
	 * @return int
	 */
	public int countAllOnDemandRequest(long companyId) {
		int count = count(SQL_COUNT_ALL, companyId);
		return count;
	}
}
