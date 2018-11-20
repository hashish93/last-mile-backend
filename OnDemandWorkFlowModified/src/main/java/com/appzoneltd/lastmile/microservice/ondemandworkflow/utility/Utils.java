package com.appzoneltd.lastmile.microservice.ondemandworkflow.utility;

import java.util.Random;

/**
 * @author Alaa Nabil
 * 
 *         UUID generator of long values
 * 
 *         with getting currentTimeMillis and concatenate in with random string
 *         Return it as a long
 * 
 * 
 */

public final class Utils {

	private static final String JPEG = "FFD8";
	private static final String PNG = "89504E47";
	
	
	private Utils() {

	}

	public static Long generateUUID() {
		StringBuilder strRetVal = new StringBuilder();
		String strTemp;
		strTemp = Long.toString(System.currentTimeMillis());
		strRetVal.append(strTemp.substring(4));

		return Long.parseLong(strRetVal.toString());
	}

	/**
	 * @param image
	 *            <b>Validating images to be JPEG or PNG </b>
	 * 
	 * @return boolean
	 */
	public static boolean validateImage(byte[] image) {
		if (image == null)
			return false;
		if (image.length <= 1)
			return false;

		final String firstHexValue = Integer.toHexString(Byte.toUnsignedInt(image[0]));
		final String SecHexValue = Integer.toHexString(Byte.toUnsignedInt(image[1]));

		if (JPEG.equalsIgnoreCase(firstHexValue + SecHexValue))
			return true;

		if (image.length < 8)
			return false;
		else {

			StringBuilder hexMagicNumbers = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				hexMagicNumbers.append(Integer.toHexString(Byte.toUnsignedInt(image[i])));
			}

			if (PNG.equalsIgnoreCase(hexMagicNumbers.toString())) {
				return true;
			}
		}

		return false;

	}
	
	
	public static String codeGenerator() {
		String result = "";
		Character data = null;
		final String codeGenerator = "0123456789ABCDEFGHIJKLMNOPQRSTVWZYZ";
		final int codeLength = codeGenerator.length();
		Random r = new Random();
		for (int i = 0; i < 25; i++) {
			data = codeGenerator.charAt(r.nextInt(codeLength));
			result += data;

		}

		return result;

	}

}
