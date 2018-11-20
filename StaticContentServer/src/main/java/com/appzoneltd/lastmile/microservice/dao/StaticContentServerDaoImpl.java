package com.appzoneltd.lastmile.microservice.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Repository
public class StaticContentServerDaoImpl extends AbstractDao<LastMileFile> implements StaticContentServerDao {

	private final ApplicationContext applicationContext;

	public StaticContentServerDaoImpl(JdbcTemplate jdbcTemplate, ApplicationContext applicationContext) {
		super(jdbcTemplate, null, SAVE_FILE_META_DATA, null, null, null, null, null, null);
		this.applicationContext = applicationContext;
	}

	@Value("${File.path}")
	private String rootFilePath;

	public Long saveFileMetaData(LastMileFile file) throws IOException {

		Long fileId = Utils.generateUUID();
		String httpContentType = null;
		String serverId = null;

		String fileFullPhysicalPath = rootFilePath + fileId;

		file.setFilePhysicalPath(fileFullPhysicalPath);
		file.setFileId(fileId);

		// to generate MIMETYPE
		httpContentType = "image/" + file.getExtension();
		serverId = applicationContext.getEnvironment().getProperty("serverId");

		save(fileId, file.getName(), file.getExtension(), httpContentType, fileFullPhysicalPath, serverId);
		return fileId;

	}

}
