package com.appzoneltd.lastmile.microservice.model;

public class LastMileFileDto {
	
	private Long fileId ; 
	
	private String checkSum ;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	} 

}
