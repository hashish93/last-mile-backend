package com.appzoneltd.lastmile.microservice.modelobjects;

import java.io.Serializable;
import java.util.List;

import com.appzoneltd.lastmile.microservice.enums.OrderBy;

public class EndPointParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6808330546279356454L;
	private Long id;
	private int page;
	private int maxResult;
	private OrderBy orderBy;
	List<Long> hubIds ;
	
	public EndPointParameter() {
	}

	public EndPointParameter(Long id, int page, int maxResult, OrderBy orderBy) {
		super();
		this.page = page;
		this.maxResult = maxResult;
		this.orderBy = orderBy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Long> getHubIds() {
		return hubIds;
	}

	public void setHubIds(List<Long> hubIds) {
		this.hubIds = hubIds;
	}
}
