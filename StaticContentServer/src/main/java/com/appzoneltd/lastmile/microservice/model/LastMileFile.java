package com.appzoneltd.lastmile.microservice.model;

import java.util.Map;

public class LastMileFile {
	
	private String name;
	private String extension;
	private String size;
	private String fileName;
	private String httpContentType;
	private String checkSum ; 
	private String base64ByteArray;
	private String serverId ;
	private Long fileId ; 
	private Long companyId;
	private String filePhysicalPath;
	private Map<String,Object> parameters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	public String getBase64ByteArray() {
		return base64ByteArray;
	}
	public void setBase64ByteArray(String base64ByteArray) {
		this.base64ByteArray = base64ByteArray;
	}
	public String getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
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
		
}
