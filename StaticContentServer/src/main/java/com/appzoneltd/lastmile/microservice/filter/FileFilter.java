package com.appzoneltd.lastmile.microservice.filter;

import java.util.List;



import com.appzoneltd.lastmile.microservice.exception.FileFilterException;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;

public abstract class FileFilter {
	
	private List<String> fileTypes;
	
	
	public void setFileTypes(List<String> fileTypes) {
		this.fileTypes = fileTypes;
	}

	public List<String> getFileTypes() {
		return fileTypes;
	}

	public abstract void execute(LastMileFile lastMileFile) throws FileFilterException;
	

}
