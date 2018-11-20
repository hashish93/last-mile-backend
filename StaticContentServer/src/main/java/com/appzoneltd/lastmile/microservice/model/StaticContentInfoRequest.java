package com.appzoneltd.lastmile.microservice.model;

public class StaticContentInfoRequest {
	private Long fileId;
	private String hostAndPort;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getHostAndPort() {
		return hostAndPort;
	}

	public void setHostAndPort(String hostAndPort) {
		this.hostAndPort = hostAndPort;
	}
}
