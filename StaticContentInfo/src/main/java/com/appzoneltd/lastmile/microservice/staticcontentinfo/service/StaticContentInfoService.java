package com.appzoneltd.lastmile.microservice.staticcontentinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.staticcontentinfo.dao.StaticContentInfoDaoImp;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfo;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfoDto;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfoRequest;

@Service
public class StaticContentInfoService {

	@Autowired
	private StaticContentInfoDaoImp staticContentInfoDaoImp;

	public StaticContentInfoDto getStaticContentMetaData(StaticContentInfoRequest staticContentInfoRequest) {
		StaticContentInfo staticContentInfo = staticContentInfoDaoImp
				.getStaticContentMetaData(staticContentInfoRequest);

		StaticContentInfoDto staticContentInfoDto = new StaticContentInfoDto();
		staticContentInfoDto.setFileId(staticContentInfoRequest.getFileId());

		StringBuilder uri = new StringBuilder();
		uri.append(staticContentInfoRequest.getHostAndPort()).append("/file/operation/")
				.append(staticContentInfo.getServerId()).append("/").append(staticContentInfoRequest.getFileId());

		staticContentInfoDto.setUri(uri.toString());

		return staticContentInfoDto;
	}

}
