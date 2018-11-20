package com.appzoneltd.lastmile.microservice.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import com.appzoneltd.lastmile.microservice.model.LastMileFile;

public class FileIOUtility {
	
	
	
	
	public static void saveBase64FileAsNormalFile(LastMileFile lastMileFile) throws IOException{	
		
		byte[] decodedBytes = Base64.getMimeDecoder().decode(lastMileFile.getBase64ByteArray());
		
		FileOutputStream fileOutputStream=new FileOutputStream(lastMileFile.getFilePhysicalPath());
		fileOutputStream.write(decodedBytes);
		fileOutputStream.flush();
		fileOutputStream.close();
		
	}
	
}
