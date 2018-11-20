package com.appzoneltd.lastmile.microservice.abstractconfig;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;

/**
 * @author Alaa Nabil
 * 
 * 
 */

public abstract class AbstractDao<T> {

	protected final JdbcTemplate jdbcTemplate;
	private final RowMapper<T> rowMapper;
	private final String insertQuery;
	private final String updateQuery;
	private final String deactivateQuery;
	private final String findAllQuery;
	private final String findOneQuery;
	private final String deleteQuery;
	private final String countAllQuery;

	public AbstractDao(JdbcTemplate jdbcTemplate, RowMapper<T> rowMapper, String insertQuery, String updateQuery,
			String deactivateQuery, String findAllQuery, String findOneQuery, String deleteQuery,
			String countAllQuery) {
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapper = rowMapper;
		this.insertQuery = insertQuery;
		this.updateQuery = updateQuery;
		this.deactivateQuery = deactivateQuery;
		this.findAllQuery = findAllQuery;
		this.findOneQuery = findOneQuery;
		this.deleteQuery = deleteQuery;
		this.countAllQuery = countAllQuery;
	}

	public int save(Object... args) {
		return jdbcTemplate.update(insertQuery, args);
	}

	/**
	 * Getting new sql query with associeted schema and perfom update query
	 */
	public int update(Object... args) {
		return jdbcTemplate.update(updateQuery, args);
	}

	/**
	 * Getting new sql query with associeted schema and perfom delete query .
	 */
	public void delete(Long id) {
		jdbcTemplate.update(deleteQuery, id);
	}

	/**
	 * Getting new sql query with associeted schema and perfom deactivate query
	 * .
	 */
	public int deactivate(Long id) {
		return jdbcTemplate.update(deactivateQuery, id);
	}

	/**
	 * Getting new sql query with associeted schema and getting data in a list .
	 */
	public List<T> query() {
		return jdbcTemplate.query(findAllQuery, rowMapper);
	}

	/**
	 * Getting new sql query with associeted schema and getting data in a list
	 * with parameters.
	 */
	public List<T> query(Object... args) {
		return jdbcTemplate.query(findAllQuery, rowMapper, args);
	}

	/**
	 * Getting new sql query with associeted schema and getting data in a single
	 * object .
	 */
	public T queryforObject() {
		return jdbcTemplate.queryForObject(findOneQuery, rowMapper);
	}

	/**
	 * Getting new sql query with associeted schema and getting data in a single
	 * object .
	 */
	public T queryforObject(Object... args) {
		return jdbcTemplate.queryForObject(findOneQuery, rowMapper, args);
	}

	/**
	 * Getting new sql query with associeted schema and getting data in a single
	 * object .
	 */
	public int count() {
		return jdbcTemplate.queryForObject(countAllQuery, Integer.class);
	}

	/**
	 * Getting new sql query with associeted schema and getting data in a single
	 * object .
	 */
	public int count(Object... args) {
		return jdbcTemplate.queryForObject(countAllQuery, Integer.class, args);
	}

	/**
	 * Getting new sql query with associeted schema and getting data in a list
	 * 
	 * @param page
	 *            : number of page requested
	 * @param maxresult
	 *            : maximum number of rows wanted to retrieve
	 * @param orderBy
	 *            : ordering resulted row.
	 */
	public List<T> queryWithPaging(int page, int pageSize, OrderBy orderBy) {
		String sqlQuery = queryBuilderWithPageAndOffset(page, pageSize, orderBy);
		return jdbcTemplate.query(sqlQuery, rowMapper);
	}

	private String queryBuilderWithPageAndOffset(int page, int pageSize, OrderBy orderBy) {
		StringBuilder sqlQuery = new StringBuilder(findAllQuery);
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

	/**
	 * Getting new sql query with associated schema and getting data in a list
	 * 
	 * @param page
	 *            : number of page requested
	 * @param maxresult
	 *            : maximum number of rows wanted to retrieve
	 * @param orderBy
	 *            : ordering resulted row.
	 */
	public List<T> queryWithPaging(int page, int pageSize, OrderBy orderBy, Object... args) {
		String sqlQuery = queryBuilderWithPageAndOffset(page, pageSize, orderBy);
		return jdbcTemplate.query(sqlQuery, rowMapper, args);
	}

	/**
	 * Method TO CHECK Business validations on any colum value
	 * 
	 * @param companyId
	 * @param module
	 * @param tableName
	 * @param column
	 * @param value
	 *            Any of these parameter MUST NOT be null .
	 * 
	 * @return boolean value which in TRUE if exist OR, FALSE if not .
	 * 
	 * 
	 * 
	 **/
	public boolean isAlreadyExists(String tableName, String column, Object value) {
		Integer count = null;
		StringBuilder sqlBuilder = new StringBuilder("SELECT count(*) ");
		sqlBuilder.append("FROM ");
		sqlBuilder.append(tableName);
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append(" status='ACTIVE' AND ");
		sqlBuilder.append(column);
		sqlBuilder.append(" = ");
		sqlBuilder.append("?");

		count = jdbcTemplate.queryForObject(sqlBuilder.toString(), Integer.class, value);

		if (count == null || count <= 0)
			return false;

		return true;
	}

	public boolean isAlreadyExistsWithoutStatus(String tableName, String column, Object value) {
		Integer count = null;
		StringBuilder sqlBuilder = new StringBuilder("SELECT count(*) ");
		sqlBuilder.append("FROM ");
		sqlBuilder.append(tableName);
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append(column);
		sqlBuilder.append(" = ");
		sqlBuilder.append("?");

		count = jdbcTemplate.queryForObject(sqlBuilder.toString(), Integer.class, value);

		if (count == null || count <= 0)
			return false;

		return true;
	}

}
