package com.appzoneltd.lastmile.microservice.createsearchtopic.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(value = Include.NON_NULL)
public class QueryDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long _ID;
	@Field
	private List<QueryModel> queryModels;
	@Field
	private Integer port;
	@Field
	private Integer serverId;

	public QueryDTO() {
	}

	public QueryDTO(Long _ID, List<QueryModel> queryModels, Integer port, Integer serverId) {
		super();
		this._ID = _ID;
		this.queryModels = queryModels;
		this.port = port;
		this.serverId = serverId;
	}

	public Long get_ID() {
		return _ID;
	}

	public void set_ID(Long _ID) {
		this._ID = _ID;
	}

	public List<QueryModel> getQueryModels() {
		return queryModels;
	}

	public void setQueryModels(List<QueryModel> queryModels) {
		this.queryModels = queryModels;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
}
