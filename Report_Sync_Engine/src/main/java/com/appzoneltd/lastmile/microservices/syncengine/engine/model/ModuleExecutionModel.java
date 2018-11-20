package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import java.util.Date;

import lombok.Data;

@Data
public class ModuleExecutionModel {

	private Long moduleId;
	private String moduleName;
	private Long seconds;
	private Date lastTimeStamp;
	
	public ModuleExecutionModel(){}

	public ModuleExecutionModel(Long moduleId, String moduleName, Long seconds, Date lastTimeStamp) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.seconds = seconds;
		this.lastTimeStamp = lastTimeStamp;
	}
	
	
}
