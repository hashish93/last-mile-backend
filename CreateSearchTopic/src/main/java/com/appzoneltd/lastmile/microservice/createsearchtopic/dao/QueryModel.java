/**
 * Sep 26, 201612:27:57 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.createsearchtopic.dao;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author alaa.nabil
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class QueryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String queryName;
	private Query query;

	public QueryModel() {

	}

	public QueryModel(String queryName, Query query) {
		this.queryName = queryName;
		this.query = query;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

}
