package com.appzoneltd.lastmile.microservice.downloadstaticcontent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.downloadstaticcontent.Dao.DeleteStaticContentDaoImpl;
import com.appzoneltd.lastmile.microservice.downloadstaticcontent.model.LastMileFile;

@Service
public class DeleteStaticContentService {

	@Autowired
	private DeleteStaticContentDaoImpl deleteStaticContentDaoImpl;

	public void deleteFileContent(LastMileFile lastMileFile) {
		deleteStaticContentDaoImpl.deleteFileMetaData(lastMileFile.getFileId());
	}

}
