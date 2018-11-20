package com.appzoneltd.lastmile.microservice.filter.impl;

import com.appzoneltd.lastmile.microservice.exception.FileFilterException;
import com.appzoneltd.lastmile.microservice.filter.FileFilter;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;
import com.appzoneltd.lastmile.microservice.utility.Utils;

import java.util.Base64;

public class ValidateImageSizeAndFormatFilter extends FileFilter {

	@Override
	public void execute(LastMileFile lastMileFile) throws FileFilterException {
		try {

			Boolean validate = Utils.validateImage(Base64.getDecoder().decode(lastMileFile.getBase64ByteArray()));
			if (validate == false) {

				throw new Exception();
			}

		} catch (Exception e) {
			throw new FileFilterException(e);

		}

	}

}
