package com.appzoneltd.lastmile.microservice.downloadstaticcontent.model;

public class LastMileFile {

	private Long fileId;
	private String filePhysicalPath;
	private String size;
	private String fileName;
	private String extension;
	private String httpContentType;
	private String serverId;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFilePhysicalPath() {
		return filePhysicalPath;
	}

	public void setFilePhysicalPath(String filePhysicalPath) {
		this.filePhysicalPath = filePhysicalPath;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

}
