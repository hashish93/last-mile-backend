package com.appzoneltd.lastmile.microservice.vehicle.json;

public class RequestParameters {

	private long vehicleId ;
	
	private long companyId;

	private int page;

	private int pagesize = -1;

	private String orderTypeStr ;

	public RequestParameters() {

	}

	public RequestParameters(long companyId, int page, int pagesize, String orderTypeStr) {
		this.companyId = companyId;
		this.page = page;
		this.pagesize = pagesize;
		this.orderTypeStr = orderTypeStr;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
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
