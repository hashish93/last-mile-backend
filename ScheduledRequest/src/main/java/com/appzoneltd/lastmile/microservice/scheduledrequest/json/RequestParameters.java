package com.appzoneltd.lastmile.microservice.scheduledrequest.json;

public class RequestParameters {
	

	private String requestId ;
	
	private String  requesterMobile ; 
	
	private String fromRequestDate ;
	
	private String toRequestDate ;
	
	private String fromPickupDate ;
	
	private String toPickupDate ;
	
	
	private long companyId;

	private int page;

	private int pagesize = -1;

	private String orderTypeStr = "ASC";
	
	
	
	public RequestParameters() {

	}
	
	
	
	
	public RequestParameters(String requestId, String requesterMobile, String fromRequestDate, String toRequestDate,
			String fromPickupDate, String toPickupDate, long companyId, int page, int pagesize, String orderTypeStr) {
		super();
		this.requestId = requestId;
		this.requesterMobile = requesterMobile;
		this.fromRequestDate = fromRequestDate;
		this.toRequestDate = toRequestDate;
		this.fromPickupDate = fromPickupDate;
		this.toPickupDate = toPickupDate;
		this.companyId = companyId;
		this.page = page;
		this.pagesize = pagesize;
		this.orderTypeStr=orderTypeStr;
		
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



	





	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getOrderTypeStr() {
		return orderTypeStr;
	}

	public void setOrderTypeStr(String OrderTypeStr) {
		this.orderTypeStr = OrderTypeStr;
	}

}
