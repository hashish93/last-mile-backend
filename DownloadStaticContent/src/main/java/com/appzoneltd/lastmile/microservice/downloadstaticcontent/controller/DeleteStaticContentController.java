package com.appzoneltd.lastmile.microservice.downloadstaticcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.downloadstaticcontent.model.LastMileFile;
import com.appzoneltd.lastmile.microservice.downloadstaticcontent.service.DeleteStaticContentService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

@RestController
public class DeleteStaticContentController {

	@Autowired
	private DeleteStaticContentService deleteStaticContentService;

	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteFileMetaData(@RequestBody LastMileFile lastMileFile) {
		deleteStaticContentService.deleteFileContent(lastMileFile);
		return new ResponseEntity<>(new Message(MessageType.SUCCESS, "Success", "FileDeleted"), HttpStatus.OK);
	}

	
}
