package com.appzoneltd.lastmile.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.FileFilterException;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;
import com.appzoneltd.lastmile.microservice.model.LastMileFileDto;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.service.FileUploadFilterChainManager;

@RestController
public class FileController {

	@Autowired
	private FileUploadFilterChainManager fileUploadFilterChainManager;

	LastMileFileDto fileInfo = new LastMileFileDto();

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveFile(@RequestBody LastMileFile lastMileFile) {
		System.out.println("FileController");
		try {
			fileUploadFilterChainManager.startFileFilterChain(lastMileFile);
		} catch (FileFilterException e) {

			return new ResponseEntity<>(
					new Message(MessageType.ERROR, "error", "Can not upload ,image format is not valid"),
					HttpStatus.BAD_REQUEST);

		}

		if (lastMileFile.getCheckSum() == null || lastMileFile.getCheckSum().equals(""))
			fileInfo.setFileId(lastMileFile.getFileId());

		else {
			fileInfo.setFileId(lastMileFile.getFileId());
			fileInfo.setCheckSum(lastMileFile.getCheckSum());
		}
		return new ResponseEntity<>(fileInfo, HttpStatus.OK);

	}

}
