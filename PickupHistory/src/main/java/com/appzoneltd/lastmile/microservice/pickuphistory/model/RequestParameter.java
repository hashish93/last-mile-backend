package com.appzoneltd.lastmile.microservice.pickuphistory.model;

public class RequestParameter {

	private long companyId;

	private int pageCount;

	private int pageNum;

	private String orderType;

	private String requestId;

	private String requesterMobile;

	private String fromRequestDate;

	private String toRequestDate;

	private String fromPickupDate;

	private String toPickupDate;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequesterMobile() {
		return requesterMobile;
	}

	public void setRequesterMobile(String requesterMobile) {
		this.requesterMobile = requesterMobile;
	}

	public String getFromRequestDate() {
		return fromRequestDate;
	}

	public void setFromRequestDate(String fromRequestDate) {
		this.fromRequestDate = fromRequestDate;
	}

	public String getToRequestDate() {
		return toRequestDate;
	}

	public void setToRequestDate(String toRequestDate) {
		this.toRequestDate = toRequestDate;
	}

	public String getFromPickupDate() {
		return fromPickupDate;
	}

	public void setFromPickupDate(String fromPickupDate) {
		this.fromPickupDate = fromPickupDate;
	}

	public String getToPickupDate() {
		return toPickupDate;
	}

	public void setToPickupDate(String toPickupDate) {
		this.toPickupDate = toPickupDate;
	}

}
