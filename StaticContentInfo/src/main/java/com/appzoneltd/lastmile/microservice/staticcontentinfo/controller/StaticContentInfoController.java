package com.appzoneltd.lastmile.microservice.staticcontentinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfoDto;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.model.StaticContentInfoRequest;
import com.appzoneltd.lastmile.microservice.staticcontentinfo.service.StaticContentInfoService;

@RestController
public class StaticContentInfoController {

	@Autowired
	private StaticContentInfoService staticContentInfoService;

	@RequestMapping(method = RequestMethod.GET,value={"{fileId}"})
	public ResponseEntity<?> getFileMetaDataByFileId(@RequestHeader("ourHostAndPort") String hostAndPort,@PathVariable("fileId") Long fileId) {		
		StaticContentInfoRequest staticContentInfoRequest=new StaticContentInfoRequest();
		staticContentInfoRequest.setHostAndPort(hostAndPort);
		staticContentInfoRequest.setFileId(fileId);

		StaticContentInfoDto result = staticContentInfoService.getStaticContentMetaData(staticContentInfoRequest);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
