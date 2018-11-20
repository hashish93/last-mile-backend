package com.appzoneltd.lastmile.microservice.filter.impl;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;


import com.appzoneltd.lastmile.microservice.dao.StaticContentServerDaoImpl;
import com.appzoneltd.lastmile.microservice.exception.FileFilterException;
import com.appzoneltd.lastmile.microservice.filter.FileFilter;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;
import com.appzoneltd.lastmile.microservice.utility.FileIOUtility;

public class SaveFileFilter extends FileFilter {

	@Autowired
	private StaticContentServerDaoImpl staticContentServerDaoImpl;

	@Override
	public void execute(LastMileFile lastMileFile) throws FileFilterException {
		try {
			
			staticContentServerDaoImpl.saveFileMetaData(lastMileFile);
			FileIOUtility.saveBase64FileAsNormalFile(lastMileFile);
		} catch (IOException e) {
			throw new FileFilterException(e);

		}

	}

}
