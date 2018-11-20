package com.appzoneltd.lastmile.microservice.model;

public class StaticContentInfo {
	private String name;
	private String extension;
	private String httpContentType;
	private String serverId;
	private Long fileId;
	private String filePhysicalPath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePhysicalPath() {
		return filePhysicalPath;
	}

	public void setFilePhysicalPath(String filePhysicalPath) {
		this.filePhysicalPath = filePhysicalPath;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getHttpContentType() {
		return httpContentType;
	}

	public void setHttpContentType(String httpContentType) {
		this.httpContentType = httpContentType;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

}
