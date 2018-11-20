package com.appzoneltd.lastmile.microservice.downloadstaticcontent.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.downloadstaticcontent.Dao.DownloadStaticContentDaoImp;
import com.appzoneltd.lastmile.microservice.downloadstaticcontent.model.DownloadStaticInfoRequest;
import com.appzoneltd.lastmile.microservice.downloadstaticcontent.model.LastMileFile;

@RestController
public class DownloadStaticContentController {

	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	@Autowired
	private DownloadStaticContentDaoImp downloadStaticContentDaoImp;

	/**
	 * Method for handling file download request from client
	 */
	@RequestMapping(value = {
			"{serverId}/{fileId}/{size}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> doDownload(@PathVariable(value = "fileId") final Long fileId, @PathVariable(value = "size") final String size,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		DownloadStaticInfoRequest downloadStaticInfoRequest = new DownloadStaticInfoRequest();
		downloadStaticInfoRequest.setFileId(fileId);

		LastMileFile fileInfo = downloadStaticContentDaoImp.getStaticContentMetaData(downloadStaticInfoRequest);

		// get absolute path of the application
		ServletContext context = request.getServletContext();

		String filePhysicalFilePath = !size.equals("original") ? fileInfo.getFilePhysicalPath() + "-" + size
				: fileInfo.getFilePhysicalPath();
		File downloadFile = new File(filePhysicalFilePath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(filePhysicalFilePath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = fileInfo.getHttpContentType();
		}

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		if (!(fileInfo.getExtension().equals("png") || fileInfo.getExtension().equals("jpg")
				|| fileInfo.getExtension().equals("jpeg"))) {
			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					fileInfo.getFileName() + "." + fileInfo.getExtension());
			response.setHeader(headerKey, headerValue);
		}

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
		return null;

	}

}
