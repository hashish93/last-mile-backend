package com.appzoneltd.lastmile.microservice.staticcontentinfo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfo;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfoRequest;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfoRowMapper;

@Repository
public class StaticContentInfoDaoImp extends AbstractDao<StaticContentInfo> implements StaticContentInfoDao {

	@Autowired
	public StaticContentInfoDaoImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new StaticContentInfoRowMapper(), null, null, null, null, GET_FILE_INFO_META_DATA, null,
				null);
	}

	public StaticContentInfo getStaticContentMetaData(StaticContentInfoRequest staticContentInfoRequest) {
		StaticContentInfo staticContentInfo = queryforObject(staticContentInfoRequest.getFileId());
		return staticContentInfo;
	}

}
