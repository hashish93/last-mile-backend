package com.appzoneltd.lastmile.microservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.model.DownloadStaticContentRowMapper;
import com.appzoneltd.lastmile.microservice.model.DownloadStaticInfoRequest;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;

@Repository
public class DownloadStaticContentDaoImp extends AbstractDao<LastMileFile> implements DownloadStaticContentDao {

	public DownloadStaticContentDaoImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new DownloadStaticContentRowMapper(), null, null, null, null, GET_FILE_INFO_META_DATA, null,
				null);
	}

	public LastMileFile getStaticContentMetaData(DownloadStaticInfoRequest downloadStaticInfoRequest) {
		LastMileFile lastMileFile = queryforObject(downloadStaticInfoRequest.getFileId());
		return lastMileFile;
	}
}
