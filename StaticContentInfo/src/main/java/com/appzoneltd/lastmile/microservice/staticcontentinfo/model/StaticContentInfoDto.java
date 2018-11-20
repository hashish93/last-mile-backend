package com.appzoneltd.lastmile.microservice.staticcontentinfo.model;

public class StaticContentInfoDto {
	private Long fileId;
	private String uri;
	
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

}
