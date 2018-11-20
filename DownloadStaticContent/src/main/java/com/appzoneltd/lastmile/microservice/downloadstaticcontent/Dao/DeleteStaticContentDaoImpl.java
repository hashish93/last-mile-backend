package com.appzoneltd.lastmile.microservice.downloadstaticcontent.Dao;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.downloadstaticcontent.model.DownloadStaticContentRowMapper;
import com.appzoneltd.lastmile.microservice.downloadstaticcontent.model.LastMileFile;

@Repository
public class DeleteStaticContentDaoImpl extends AbstractDao<LastMileFile> implements DeleteStaticContentDao {

	@Autowired
	public DeleteStaticContentDaoImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new DownloadStaticContentRowMapper(), null, null, DELETE_FILE_META_DATA, null, null, null,
				null);
	}

	// delete file and resizable files with fileId in the physical path
	public void actualDelete(String file) {
		File deletedFile = new File(System.getProperty("user.dir") + File.separator + "images" + File.separator + file);
		deletedFile.delete();
	}

	public void deleteFileMetaData(Long fileId) {
		actualDelete(File.separator + fileId);
		actualDelete(File.separator + fileId + "-large");
		actualDelete(File.separator + fileId + "-medium");
		actualDelete(File.separator + fileId + "-small");

		// update file meta data in db
		deactivate(fileId);

	}

}
