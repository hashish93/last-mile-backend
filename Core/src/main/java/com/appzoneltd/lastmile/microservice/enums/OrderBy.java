package com.appzoneltd.lastmile.microservice.enums;

/**
 * @author Alaa Nabil Enumeration class to have order by type.
 **/
public enum OrderBy {
	ASC("ASC"), DESC("DESC");

	private final String orderBy;

	private OrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderBy() {
		return orderBy;
	}
}
