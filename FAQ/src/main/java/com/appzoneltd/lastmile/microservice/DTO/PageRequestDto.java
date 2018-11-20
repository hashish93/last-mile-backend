package com.appzoneltd.lastmile.microservice.DTO;

import lombok.Data;

/**
 * Created by hashish on 4/25/2017.
 */
//@Data
public class PageRequestDto {
    private int pageOffset;
    private int pageSize;
    private String orderBy;
	public int getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
   

	
}
